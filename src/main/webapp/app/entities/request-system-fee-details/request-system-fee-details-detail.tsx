import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './request-system-fee-details.reducer';

export const RequestSystemFeeDetailsDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const requestSystemFeeDetailsEntity = useAppSelector(state => state.requestSystemFeeDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="requestSystemFeeDetailsDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.detail.title">RequestSystemFeeDetails</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeDetailsEntity.id}</dd>
          <dt>
            <span id="feeCode">
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.feeCode">Fee Code</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeDetailsEntity.feeCode}</dd>
          <dt>
            <span id="feeNameAr">
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.feeNameAr">Fee Name Ar</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeDetailsEntity.feeNameAr}</dd>
          <dt>
            <span id="feeNameEn">
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.feeNameEn">Fee Name En</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeDetailsEntity.feeNameEn}</dd>
          <dt>
            <span id="feeAmount">
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.feeAmount">Fee Amount</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeDetailsEntity.feeAmount}</dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.requestSystemFee">Request System Fee</Translate>
          </dt>
          <dd>{requestSystemFeeDetailsEntity.requestSystemFee ? requestSystemFeeDetailsEntity.requestSystemFee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/request-system-fee-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/request-system-fee-details/${requestSystemFeeDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RequestSystemFeeDetailsDetail;
