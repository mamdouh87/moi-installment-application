import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { IRequest } from 'app/shared/model/request.model';
import { getEntity, updateEntity, createEntity, reset } from './request.reducer';

export const RequestUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const customers = useAppSelector(state => state.customer.entities);
  const requestEntity = useAppSelector(state => state.request.entity);
  const loading = useAppSelector(state => state.request.loading);
  const updating = useAppSelector(state => state.request.updating);
  const updateSuccess = useAppSelector(state => state.request.updateSuccess);
  const handleClose = () => {
    props.history.push('/request' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getCustomers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...requestEntity,
      ...values,
      customer: customers.find(it => it.id.toString() === values.customer.toString()),
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
          ...requestEntity,
          customer: requestEntity?.customer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="moiInstallmentApplicationApp.request.home.createOrEditLabel" data-cy="RequestCreateUpdateHeading">
            <Translate contentKey="moiInstallmentApplicationApp.request.home.createOrEditLabel">Create or edit a Request</Translate>
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
                  id="request-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.requestNo')}
                id="request-requestNo"
                name="requestNo"
                data-cy="requestNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.requestServiceCode')}
                id="request-requestServiceCode"
                name="requestServiceCode"
                data-cy="requestServiceCode"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.requestDescription')}
                id="request-requestDescription"
                name="requestDescription"
                data-cy="requestDescription"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.vehicleTypeId')}
                id="request-vehicleTypeId"
                name="vehicleTypeId"
                data-cy="vehicleTypeId"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.licenseTypeId')}
                id="request-licenseTypeId"
                name="licenseTypeId"
                data-cy="licenseTypeId"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.plateNo')}
                id="request-plateNo"
                name="plateNo"
                data-cy="plateNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.motorNo')}
                id="request-motorNo"
                name="motorNo"
                data-cy="motorNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.chassisNo')}
                id="request-chassisNo"
                name="chassisNo"
                data-cy="chassisNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.request.trafficUnitCode')}
                id="request-trafficUnitCode"
                name="trafficUnitCode"
                data-cy="trafficUnitCode"
                type="text"
              />
              <ValidatedField
                id="request-customer"
                name="customer"
                data-cy="customer"
                label={translate('moiInstallmentApplicationApp.request.customer')}
                type="select"
              >
                <option value="" key="0" />
                {customers
                  ? customers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/request" replace color="info">
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

export default RequestUpdate;
