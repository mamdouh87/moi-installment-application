import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContract } from 'app/shared/model/contract.model';
import { getEntities as getContracts } from 'app/entities/contract/contract.reducer';
import { IContractSystemFee } from 'app/shared/model/contract-system-fee.model';
import { getEntity, updateEntity, createEntity, reset } from './contract-system-fee.reducer';

export const ContractSystemFeeUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contracts = useAppSelector(state => state.contract.entities);
  const contractSystemFeeEntity = useAppSelector(state => state.contractSystemFee.entity);
  const loading = useAppSelector(state => state.contractSystemFee.loading);
  const updating = useAppSelector(state => state.contractSystemFee.updating);
  const updateSuccess = useAppSelector(state => state.contractSystemFee.updateSuccess);
  const handleClose = () => {
    props.history.push('/contract-system-fee' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getContracts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.statusDate = convertDateTimeToServer(values.statusDate);

    const entity = {
      ...contractSystemFeeEntity,
      ...values,
      contract: contracts.find(it => it.id.toString() === values.contract.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          statusDate: displayDefaultDateTime(),
        }
      : {
          ...contractSystemFeeEntity,
          statusDate: convertDateTimeFromServer(contractSystemFeeEntity.statusDate),
          contract: contractSystemFeeEntity?.contract?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="moiInstallmentApplicationApp.contractSystemFee.home.createOrEditLabel" data-cy="ContractSystemFeeCreateUpdateHeading">
            <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.home.createOrEditLabel">
              Create or edit a ContractSystemFee
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="contract-system-fee-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFee.systemcode')}
                id="contract-system-fee-systemcode"
                name="systemcode"
                data-cy="systemcode"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFee.systemNameAr')}
                id="contract-system-fee-systemNameAr"
                name="systemNameAr"
                data-cy="systemNameAr"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFee.systemNameEn')}
                id="contract-system-fee-systemNameEn"
                name="systemNameEn"
                data-cy="systemNameEn"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFee.systemTotalFees')}
                id="contract-system-fee-systemTotalFees"
                name="systemTotalFees"
                data-cy="systemTotalFees"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFee.status')}
                id="contract-system-fee-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFee.statusDate')}
                id="contract-system-fee-statusDate"
                name="statusDate"
                data-cy="statusDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="contract-system-fee-contract"
                name="contract"
                data-cy="contract"
                label={translate('moiInstallmentApplicationApp.contractSystemFee.contract')}
                type="select"
              >
                <option value="" key="0" />
                {contracts
                  ? contracts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract-system-fee" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ContractSystemFeeUpdate;
