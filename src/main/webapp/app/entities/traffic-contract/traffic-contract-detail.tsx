import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './traffic-contract.reducer';

export const TrafficContractDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const trafficContractEntity = useAppSelector(state => state.trafficContract.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="trafficContractDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.trafficContract.detail.title">TrafficContract</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.id}</dd>
          <dt>
            <span id="requestId">
              <Translate contentKey="moiInstallmentApplicationApp.trafficContract.requestId">Request Id</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.requestId}</dd>
          <dt>
            <span id="requestNo">
              <Translate contentKey="moiInstallmentApplicationApp.trafficContract.requestNo">Request No</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.requestNo}</dd>
          <dt>
            <span id="requestServiceCode">
              <Translate contentKey="moiInstallmentApplicationApp.trafficContract.requestServiceCode">Request Service Code</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.requestServiceCode}</dd>
          <dt>
            <span id="requestDescription">
              <Translate contentKey="moiInstallmentApplicationApp.trafficContract.requestDescription">Request Description</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.requestDescription}</dd>
          <dt>
            <span id="plateNo">
              <Translate contentKey="moiInstallmentApplicationApp.trafficContract.plateNo">Plate No</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.plateNo}</dd>
          <dt>
            <span id="motorNo">
              <Translate contentKey="moiInstallmentApplicationApp.trafficContract.motorNo">Motor No</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.motorNo}</dd>
          <dt>
            <span id="chassisNo">
              <Translate contentKey="moiInstallmentApplicationApp.trafficContract.chassisNo">Chassis No</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.chassisNo}</dd>
          <dt>
            <span id="trafficUnitCode">
              <Translate contentKey="moiInstallmentApplicationApp.trafficContract.trafficUnitCode">Traffic Unit Code</Translate>
            </span>
          </dt>
          <dd>{trafficContractEntity.trafficUnitCode}</dd>
        </dl>
        <Button tag={Link} to="/traffic-contract" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/traffic-contract/${trafficContractEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TrafficContractDetail;
