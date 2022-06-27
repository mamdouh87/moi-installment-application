import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './customer.reducer';

export const CustomerDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const customerEntity = useAppSelector(state => state.customer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customerDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.customer.detail.title">Customer</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customerEntity.id}</dd>
          <dt>
            <span id="nationalId">
              <Translate contentKey="moiInstallmentApplicationApp.customer.nationalId">National Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.nationalId}</dd>
          <dt>
            <span id="passportNo">
              <Translate contentKey="moiInstallmentApplicationApp.customer.passportNo">Passport No</Translate>
            </span>
          </dt>
          <dd>{customerEntity.passportNo}</dd>
          <dt>
            <span id="countryId">
              <Translate contentKey="moiInstallmentApplicationApp.customer.countryId">Country Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.countryId}</dd>
          <dt>
            <span id="mobileNo">
              <Translate contentKey="moiInstallmentApplicationApp.customer.mobileNo">Mobile No</Translate>
            </span>
          </dt>
          <dd>{customerEntity.mobileNo}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="moiInstallmentApplicationApp.customer.address">Address</Translate>
            </span>
          </dt>
          <dd>{customerEntity.address}</dd>
          <dt>
            <span id="fullName">
              <Translate contentKey="moiInstallmentApplicationApp.customer.fullName">Full Name</Translate>
            </span>
          </dt>
          <dd>{customerEntity.fullName}</dd>
          <dt>
            <span id="tradeLicense">
              <Translate contentKey="moiInstallmentApplicationApp.customer.tradeLicense">Trade License</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tradeLicense}</dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.customer.country">Country</Translate>
          </dt>
          <dd>{customerEntity.country ? customerEntity.country.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer/${customerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomerDetail;
