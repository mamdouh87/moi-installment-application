import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRequestSystemFee } from 'app/shared/model/request-system-fee.model';
import { getEntities as getRequestSystemFees } from 'app/entities/request-system-fee/request-system-fee.reducer';
import { IRequestSystemFeeDetails } from 'app/shared/model/request-system-fee-details.model';
import { getEntity, updateEntity, createEntity, reset } from './request-system-fee-details.reducer';

export const RequestSystemFeeDetailsUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const requestSystemFees = useAppSelector(state => state.requestSystemFee.entities);
  const requestSystemFeeDetailsEntity = useAppSelector(state => state.requestSystemFeeDetails.entity);
  const loading = useAppSelector(state => state.requestSystemFeeDetails.loading);
  const updating = useAppSelector(state => state.requestSystemFeeDetails.updating);
  const updateSuccess = useAppSelector(state => state.requestSystemFeeDetails.updateSuccess);
  const handleClose = () => {
    props.history.push('/request-system-fee-details' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getRequestSystemFees({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...requestSystemFeeDetailsEntity,
      ...values,
      requestSystemFee: requestSystemFees.find(it => it.id.toString() === values.requestSystemFee.toString()),
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
          ...requestSystemFeeDetailsEntity,
          requestSystemFee: requestSystemFeeDetailsEntity?.requestSystemFee?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="moiInstallmentApplicationApp.requestSystemFeeDetails.home.createOrEditLabel"
            data-cy="RequestSystemFeeDetailsCreateUpdateHeading"
          >
            <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.home.createOrEditLabel">
              Create or edit a RequestSystemFeeDetails
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
                  id="request-system-fee-details-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.requestSystemFeeDetails.feeCode')}
                id="request-system-fee-details-feeCode"
                name="feeCode"
                data-cy="feeCode"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.requestSystemFeeDetails.feeNameAr')}
                id="request-system-fee-details-feeNameAr"
                name="feeNameAr"
                data-cy="feeNameAr"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.requestSystemFeeDetails.feeNameEn')}
                id="request-system-fee-details-feeNameEn"
                name="feeNameEn"
                data-cy="feeNameEn"
                type="text"
              />
              <ValidatedField
                label={translate('moiInstallmentApplicationApp.requestSystemFeeDetails.feeAmount')}
                id="request-system-fee-details-feeAmount"
                name="feeAmount"
                data-cy="feeAmount"
                type="text"
              />
              <ValidatedField
                id="request-system-fee-details-requestSystemFee"
                name="requestSystemFee"
                data-cy="requestSystemFee"
                label={translate('moiInstallmentApplicationApp.requestSystemFeeDetails.requestSystemFee')}
                type="select"
              >
                <option value="" key="0" />
                {requestSystemFees
                  ? requestSystemFees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/request-system-fee-details" replace color="info">
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

export default RequestSystemFeeDetailsUpdate;
