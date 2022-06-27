import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInstallmentsPlan } from 'app/shared/model/installments-plan.model';
import { getEntity, updateEntity, createEntity, reset } from './installments-plan.reducer';

export const InstallmentsPlanUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const installmentsPlanEntity = useAppSelector(state => state.installmentsPlan.entity);
  const loading = useAppSelector(state => state.installmentsPlan.loading);
  const updating = useAppSelector(state => state.installmentsPlan.updating);
  const updateSuccess = useAppSelector(state => state.installmentsPlan.updateSuccess);
  const handleClose = () => {
    props.history.push('/installments-plan' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...installmentsPlanEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...installmentsPlanEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="moiInstallmentApplicationApp.installmentsPlan.home.createOrEditLabel" data-cy="InstallmentsPlanCreateUpdateHeading">
            <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.home.createOrEditLabel">
              Create or edit a InstallmentsPlan
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
                  id="installments-plan-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.status')}
                id="installments-plan-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.nameAr')}
                id="installments-plan-nameAr"
                name="nameAr"
                data-cy="nameAr"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.nameEn')}
                id="installments-plan-nameEn"
                name="nameEn"
                data-cy="nameEn"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.numberOfInstallments')}
                id="installments-plan-numberOfInstallments"
                name="numberOfInstallments"
                data-cy="numberOfInstallments"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.installmentIntervalDays')}
                id="installments-plan-installmentIntervalDays"
                name="installmentIntervalDays"
                data-cy="installmentIntervalDays"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.interestRate')}
                id="installments-plan-interestRate"
                name="interestRate"
                data-cy="interestRate"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.installmentGraceDays')}
                id="installments-plan-installmentGraceDays"
                name="installmentGraceDays"
                data-cy="installmentGraceDays"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.dailyLatePercentage')}
                id="installments-plan-dailyLatePercentage"
                name="dailyLatePercentage"
                data-cy="dailyLatePercentage"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.dailyLateFee')}
                id="installments-plan-dailyLateFee"
                name="dailyLateFee"
                data-cy="dailyLateFee"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.maxTotalAmount')}
                id="installments-plan-maxTotalAmount"
                name="maxTotalAmount"
                data-cy="maxTotalAmount"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.minTotalAmount')}
                id="installments-plan-minTotalAmount"
                name="minTotalAmount"
                data-cy="minTotalAmount"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.minFirstInstallmentAmount')}
                id="installments-plan-minFirstInstallmentAmount"
                name="minFirstInstallmentAmount"
                data-cy="minFirstInstallmentAmount"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.installmentsPlan.creationFees')}
                id="installments-plan-creationFees"
                name="creationFees"
                data-cy="creationFees"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/installments-plan" replace color="info">
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

export default InstallmentsPlanUpdate;
