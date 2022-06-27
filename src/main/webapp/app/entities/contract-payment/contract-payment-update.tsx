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
import { IContractPayment } from 'app/shared/model/contract-payment.model';
import { getEntity, updateEntity, createEntity, reset } from './contract-payment.reducer';

export const ContractPaymentUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contracts = useAppSelector(state => state.contract.entities);
  const contractPaymentEntity = useAppSelector(state => state.contractPayment.entity);
  const loading = useAppSelector(state => state.contractPayment.loading);
  const updating = useAppSelector(state => state.contractPayment.updating);
  const updateSuccess = useAppSelector(state => state.contractPayment.updateSuccess);
  const handleClose = () => {
    props.history.push('/contract-payment' + props.location.search);
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
    values.installmentDate = convertDateTimeToServer(values.installmentDate);
    values.paymentDate = convertDateTimeToServer(values.paymentDate);

    const entity = {
      ...contractPaymentEntity,
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
          installmentDate: displayDefaultDateTime(),
          paymentDate: displayDefaultDateTime(),
        }
      : {
          ...contractPaymentEntity,
          installmentDate: convertDateTimeFromServer(contractPaymentEntity.installmentDate),
          paymentDate: convertDateTimeFromServer(contractPaymentEntity.paymentDate),
          contract: contractPaymentEntity?.contract?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="moiInstallmentApplicationApp.contractPayment.home.createOrEditLabel" data-cy="ContractPaymentCreateUpdateHeading">
            <Translate contentKey="moiInstallmentApplicationApp.contractPayment.home.createOrEditLabel">
              Create or edit a ContractPayment
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
                  id="contract-payment-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.status')}
                id="contract-payment-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.installmentNo')}
                id="contract-payment-installmentNo"
                name="installmentNo"
                data-cy="installmentNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.installmentAmount')}
                id="contract-payment-installmentAmount"
                name="installmentAmount"
                data-cy="installmentAmount"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.installmentDate')}
                id="contract-payment-installmentDate"
                name="installmentDate"
                data-cy="installmentDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.installmentLateFees')}
                id="contract-payment-installmentLateFees"
                name="installmentLateFees"
                data-cy="installmentLateFees"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.paymentDate')}
                id="contract-payment-paymentDate"
                name="paymentDate"
                data-cy="paymentDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.paymentType')}
                id="contract-payment-paymentType"
                name="paymentType"
                data-cy="paymentType"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.receiptNo')}
                id="contract-payment-receiptNo"
                name="receiptNo"
                data-cy="receiptNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contractPayment.creationFees')}
                id="contract-payment-creationFees"
                name="creationFees"
                data-cy="creationFees"
                type="text"
              />
              <ValidatedField
                id="contract-payment-contract"
                name="contract"
                data-cy="contract"
                label={translate('moiInstallmentApplicationApp.contractPayment.contract')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract-payment" replace color="info">
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

export default ContractPaymentUpdate;
