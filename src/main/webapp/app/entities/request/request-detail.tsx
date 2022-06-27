import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './request.reducer';

export const RequestDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const requestEntity = useAppSelector(state => state.request.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="requestDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.request.detail.title">Request</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{requestEntity.id}</dd>
          <dt>
            <span id="requestNo">
              <Translate contentKey="moiInstallmentApplicationApp.request.requestNo">Request No</Translate>
            </span>
          </dt>
          <dd>{requestEntity.requestNo}</dd>
          <dt>
            <span id="requestServiceCode">
              <Translate contentKey="moiInstallmentApplicationApp.request.requestServiceCode">Request Service Code</Translate>
            </span>
          </dt>
          <dd>{requestEntity.requestServiceCode}</dd>
          <dt>
            <span id="requestDescription">
              <Translate contentKey="moiInstallmentApplicationApp.request.requestDescription">Request Description</Translate>
            </span>
          </dt>
          <dd>{requestEntity.requestDescription}</dd>
          <dt>
            <span id="vehicleTypeId">
              <Translate contentKey="moiInstallmentApplicationApp.request.vehicleTypeId">Vehicle Type Id</Translate>
            </span>
          </dt>
          <dd>{requestEntity.vehicleTypeId}</dd>
          <dt>
            <span id="licenseTypeId">
              <Translate contentKey="moiInstallmentApplicationApp.request.licenseTypeId">License Type Id</Translate>
            </span>
          </dt>
          <dd>{requestEntity.licenseTypeId}</dd>
          <dt>
            <span id="plateNo">
              <Translate contentKey="moiInstallmentApplicationApp.request.plateNo">Plate No</Translate>
            </span>
          </dt>
          <dd>{requestEntity.plateNo}</dd>
          <dt>
            <span id="motorNo">
              <Translate contentKey="moiInstallmentApplicationApp.request.motorNo">Motor No</Translate>
            </span>
          </dt>
          <dd>{requestEntity.motorNo}</dd>
          <dt>
            <span id="chassisNo">
              <Translate contentKey="moiInstallmentApplicationApp.request.chassisNo">Chassis No</Translate>
            </span>
          </dt>
          <dd>{requestEntity.chassisNo}</dd>
          <dt>
            <span id="trafficUnitCode">
              <Translate contentKey="moiInstallmentApplicationApp.request.trafficUnitCode">Traffic Unit Code</Translate>
            </span>
          </dt>
          <dd>{requestEntity.trafficUnitCode}</dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.request.customer">Customer</Translate>
          </dt>
          <dd>{requestEntity.customer ? requestEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/request" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/request/${requestEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RequestDetail;
