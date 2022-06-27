import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITrafficContract } from 'app/shared/model/traffic-contract.model';
import { getEntities as getTrafficContracts } from 'app/entities/traffic-contract/traffic-contract.reducer';
import { IInstallmentsPlan } from 'app/shared/model/installments-plan.model';
import { getEntities as getInstallmentsPlans } from 'app/entities/installments-plan/installments-plan.reducer';
import { IContract } from 'app/shared/model/contract.model';
import { getEntity, updateEntity, createEntity, reset } from './contract.reducer';

export const ContractUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const trafficContracts = useAppSelector(state => state.trafficContract.entities);
  const installmentsPlans = useAppSelector(state => state.installmentsPlan.entities);
  const contractEntity = useAppSelector(state => state.contract.entity);
  const loading = useAppSelector(state => state.contract.loading);
  const updating = useAppSelector(state => state.contract.updating);
  const updateSuccess = useAppSelector(state => state.contract.updateSuccess);
  const handleClose = () => {
    props.history.push('/contract' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getTrafficContracts({}));
    dispatch(getInstallmentsPlans({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.signDate = convertDateTimeToServer(values.signDate);

    const entity = {
      ...contractEntity,
      ...values,
      trafficContract: trafficContracts.find(it => it.id.toString() === values.trafficContract.toString()),
      installmentPlan: installmentsPlans.find(it => it.id.toString() === values.installmentPlan.toString()),
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
          signDate: displayDefaultDateTime(),
        }
      : {
          ...contractEntity,
          signDate: convertDateTimeFromServer(contractEntity.signDate),
          trafficContract: contractEntity?.trafficContract?.id,
          installmentPlan: contractEntity?.installmentPlan?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="moiInstallmentApplicationApp.contract.home.createOrEditLabel" data-cy="ContractCreateUpdateHeading">
            <Translate contentKey="moiInstallmentApplicationApp.contract.home.createOrEditLabel">Create or edit a Contract</Translate>
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
                  id="contract-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.contractNo')}
                id="contract-contractNo"
                name="contractNo"
                data-cy="contractNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.status')}
                id="contract-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.mobileNo')}
                id="contract-mobileNo"
                name="mobileNo"
                data-cy="mobileNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.address')}
                id="contract-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.fullName')}
                id="contract-fullName"
                name="fullName"
                data-cy="fullName"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.customerId')}
                id="contract-customerId"
                name="customerId"
                data-cy="customerId"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.nationalId')}
                id="contract-nationalId"
                name="nationalId"
                data-cy="nationalId"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.passportNo')}
                id="contract-passportNo"
                name="passportNo"
                data-cy="passportNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.countryId')}
                id="contract-countryId"
                name="countryId"
                data-cy="countryId"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.tradeLicense')}
                id="contract-tradeLicense"
                name="tradeLicense"
                data-cy="tradeLicense"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.signDate')}
                id="contract-signDate"
                name="signDate"
                data-cy="signDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.userId')}
                id="contract-userId"
                name="userId"
                data-cy="userId"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.actualContractedAmount')}
                id="contract-actualContractedAmount"
                name="actualContractedAmount"
                data-cy="actualContractedAmount"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.interestPercentage')}
                id="contract-interestPercentage"
                name="interestPercentage"
                data-cy="interestPercentage"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.contract.contractAmount')}
                id="contract-contractAmount"
                name="contractAmount"
                data-cy="contractAmount"
                type="text"
              />
              <ValidatedField
                id="contract-trafficContract"
                name="trafficContract"
                data-cy="trafficContract"
                label={translate('moiInstallmentApplicationApp.contract.trafficContract')}
                type="select"
              >
                <option value="" key="0" />
                {trafficContracts
                  ? trafficContracts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="contract-installmentPlan"
                name="installmentPlan"
                data-cy="installmentPlan"
                label={translate('moiInstallmentApplicationApp.contract.installmentPlan')}
                type="select"
              >
                <option value="" key="0" />
                {installmentsPlans
                  ? installmentsPlans.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract" replace color="info">
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

export default ContractUpdate;
