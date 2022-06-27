import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRequest } from 'app/shared/model/request.model';
import { getEntities as getRequests } from 'app/entities/request/request.reducer';
import { IRequestSystemFee } from 'app/shared/model/request-system-fee.model';
import { getEntity, updateEntity, createEntity, reset } from './request-system-fee.reducer';

export const RequestSystemFeeUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const requests = useAppSelector(state => state.request.entities);
  const requestSystemFeeEntity = useAppSelector(state => state.requestSystemFee.entity);
  const loading = useAppSelector(state => state.requestSystemFee.loading);
  const updating = useAppSelector(state => state.requestSystemFee.updating);
  const updateSuccess = useAppSelector(state => state.requestSystemFee.updateSuccess);
  const handleClose = () => {
    props.history.push('/request-system-fee' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getRequests({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...requestSystemFeeEntity,
      ...values,
      request: requests.find(it => it.id.toString() === values.request.toString()),
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
          ...requestSystemFeeEntity,
          request: requestSystemFeeEntity?.request?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="moiInstallmentApplicationApp.requestSystemFee.home.createOrEditLabel" data-cy="RequestSystemFeeCreateUpdateHeading">
            <Translate contentKey="moiInstallmentApplicationApp.requestSystemFee.home.createOrEditLabel">
              Create or edit a RequestSystemFee
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
                  id="request-system-fee-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.requestSystemFee.systemcode')}
                id="request-system-fee-systemcode"
                name="systemcode"
                data-cy="systemcode"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.requestSystemFee.systemNameAr')}
                id="request-system-fee-systemNameAr"
                name="systemNameAr"
                data-cy="systemNameAr"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.requestSystemFee.systemNameEn')}
                id="request-system-fee-systemNameEn"
                name="systemNameEn"
                data-cy="systemNameEn"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.requestSystemFee.systemTotalFees')}
                id="request-system-fee-systemTotalFees"
                name="systemTotalFees"
                data-cy="systemTotalFees"
                type="text"
              />
              <ValidatedField
                id="request-system-fee-request"
                name="request"
                data-cy="request"
                label={translate('moiInstallmentApplicationApp.requestSystemFee.request')}
                type="select"
              >
                <option value="" key="0" />
                {requests
                  ? requests.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/request-system-fee" replace color="info">
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

export default RequestSystemFeeUpdate;
