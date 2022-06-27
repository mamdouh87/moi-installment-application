import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITrafficContract } from 'app/shared/model/traffic-contract.model';
import { getEntity, updateEntity, createEntity, reset } from './traffic-contract.reducer';

export const TrafficContractUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const trafficContractEntity = useAppSelector(state => state.trafficContract.entity);
  const loading = useAppSelector(state => state.trafficContract.loading);
  const updating = useAppSelector(state => state.trafficContract.updating);
  const updateSuccess = useAppSelector(state => state.trafficContract.updateSuccess);
  const handleClose = () => {
    props.history.push('/traffic-contract' + props.location.search);
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
      ...trafficContractEntity,
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
          ...trafficContractEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="moiInstallmentApplicationApp.trafficContract.home.createOrEditLabel" data-cy="TrafficContractCreateUpdateHeading">
            <Translate contentKey="moiInstallmentApplicationApp.trafficContract.home.createOrEditLabel">
              Create or edit a TrafficContract
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
                  id="traffic-contract-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.trafficContract.requestId')}
                id="traffic-contract-requestId"
                name="requestId"
                data-cy="requestId"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.trafficContract.requestNo')}
                id="traffic-contract-requestNo"
                name="requestNo"
                data-cy="requestNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.trafficContract.requestServiceCode')}
                id="traffic-contract-requestServiceCode"
                name="requestServiceCode"
                data-cy="requestServiceCode"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.trafficContract.requestDescription')}
                id="traffic-contract-requestDescription"
                name="requestDescription"
                data-cy="requestDescription"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.trafficContract.plateNo')}
                id="traffic-contract-plateNo"
                name="plateNo"
                data-cy="plateNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.trafficContract.motorNo')}
                id="traffic-contract-motorNo"
                name="motorNo"
                data-cy="motorNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.trafficContract.chassisNo')}
                id="traffic-contract-chassisNo"
                name="chassisNo"
                data-cy="chassisNo"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.trafficContract.trafficUnitCode')}
                id="traffic-contract-trafficUnitCode"
                name="trafficUnitCode"
                data-cy="trafficUnitCode"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/traffic-contract" replace color="info">
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

export default TrafficContractUpdate;
