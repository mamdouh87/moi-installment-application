import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './request-system-fee.reducer';

export const RequestSystemFeeDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const requestSystemFeeEntity = useAppSelector(state => state.requestSystemFee.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="requestSystemFeeDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.requestSystemFee.detail.title">RequestSystemFee</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeEntity.id}</dd>
          <dt>
            <span id="systemcode">
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFee.systemcode">Systemcode</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeEntity.systemcode}</dd>
          <dt>
            <span id="systemNameAr">
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFee.systemNameAr">System Name Ar</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeEntity.systemNameAr}</dd>
          <dt>
            <span id="systemNameEn">
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFee.systemNameEn">System Name En</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeEntity.systemNameEn}</dd>
          <dt>
            <span id="systemTotalFees">
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFee.systemTotalFees">System Total Fees</Translate>
            </span>
          </dt>
          <dd>{requestSystemFeeEntity.systemTotalFees}</dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.requestSystemFee.request">Request</Translate>
          </dt>
          <dd>{requestSystemFeeEntity.request ? requestSystemFeeEntity.request.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/request-system-fee" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/request-system-fee/${requestSystemFeeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RequestSystemFeeDetail;
