import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContractSystemFee } from 'app/shared/model/contract-system-fee.model';
import { getEntities as getContractSystemFees } from 'app/entities/contract-system-fee/contract-system-fee.reducer';
import { IContractSystemFeeDetails } from 'app/shared/model/contract-system-fee-details.model';
import { getEntity, updateEntity, createEntity, reset } from './contract-system-fee-details.reducer';

export const ContractSystemFeeDetailsUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contractSystemFees = useAppSelector(state => state.contractSystemFee.entities);
  const contractSystemFeeDetailsEntity = useAppSelector(state => state.contractSystemFeeDetails.entity);
  const loading = useAppSelector(state => state.contractSystemFeeDetails.loading);
  const updating = useAppSelector(state => state.contractSystemFeeDetails.updating);
  const updateSuccess = useAppSelector(state => state.contractSystemFeeDetails.updateSuccess);
  const handleClose = () => {
    props.history.push('/contract-system-fee-details' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getContractSystemFees({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.statusDate = convertDateTimeToServer(values.statusDate);

    const entity = {
      ...contractSystemFeeDetailsEntity,
      ...values,
      contractSystemFee: contractSystemFees.find(it => it.id.toString() === values.contractSystemFee.toString()),
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
          ...contractSystemFeeDetailsEntity,
          statusDate: convertDateTimeFromServer(contractSystemFeeDetailsEntity.statusDate),
          contractSystemFee: contractSystemFeeDetailsEntity?.contractSystemFee?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="moiInstallmentApplicationApp.contractSystemFeeDetails.home.createOrEditLabel"
            data-cy="ContractSystemFeeDetailsCreateUpdateHeading"
          >
            <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.home.createOrEditLabel">
              Create or edit a ContractSystemFeeDetails
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
                  id="contract-system-fee-details-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFeeDetails.feeCode')}
                id="contract-system-fee-details-feeCode"
                name="feeCode"
                data-cy="feeCode"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFeeDetails.feeNameAr')}
                id="contract-system-fee-details-feeNameAr"
                name="feeNameAr"
                data-cy="feeNameAr"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFeeDetails.feeNameEn')}
                id="contract-system-fee-details-feeNameEn"
                name="feeNameEn"
                data-cy="feeNameEn"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFeeDetails.feeAmount')}
                id="contract-system-fee-details-feeAmount"
                name="feeAmount"
                data-cy="feeAmount"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFeeDetails.status')}
                id="contract-system-fee-details-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFeeDetails.statusDate')}
                id="contract-system-fee-details-statusDate"
                name="statusDate"
                data-cy="statusDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractSystemFeeDetails.draftContractSystemFee')}
                id="contract-system-fee-details-draftContractSystemFee"
                name="draftContractSystemFee"
                data-cy="draftContractSystemFee"
                type="text"
              />
              <ValidatedField
                id="contract-system-fee-details-contractSystemFee"
                name="contractSystemFee"
                data-cy="contractSystemFee"
                label={translate('moiInstallmentApplicationApp.contractSystemFeeDetails.contractSystemFee')}
                type="select"
              >
                <option value="" key="0" />
                {contractSystemFees
                  ? contractSystemFees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract-system-fee-details" replace color="info">
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

export default ContractSystemFeeDetailsUpdate;
