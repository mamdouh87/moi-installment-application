import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './installments-plan.reducer';

export const InstallmentsPlanDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const installmentsPlanEntity = useAppSelector(state => state.installmentsPlan.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="installmentsPlanDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.detail.title">InstallmentsPlan</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.id}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.status">Status</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.status}</dd>
          <dt>
            <span id="nameAr">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.nameAr">Name Ar</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.nameAr}</dd>
          <dt>
            <span id="nameEn">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.nameEn">Name En</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.nameEn}</dd>
          <dt>
            <span id="numberOfInstallments">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.numberOfInstallments">Number Of Installments</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.numberOfInstallments}</dd>
          <dt>
            <span id="installmentIntervalDays">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.installmentIntervalDays">
                Installment Interval Days
              </Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.installmentIntervalDays}</dd>
          <dt>
            <span id="interestRate">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.interestRate">Interest Rate</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.interestRate}</dd>
          <dt>
            <span id="installmentGraceDays">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.installmentGraceDays">Installment Grace Days</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.installmentGraceDays}</dd>
          <dt>
            <span id="dailyLatePercentage">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.dailyLatePercentage">Daily Late Percentage</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.dailyLatePercentage}</dd>
          <dt>
            <span id="dailyLateFee">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.dailyLateFee">Daily Late Fee</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.dailyLateFee}</dd>
          <dt>
            <span id="maxTotalAmount">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.maxTotalAmount">Max Total Amount</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.maxTotalAmount}</dd>
          <dt>
            <span id="minTotalAmount">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.minTotalAmount">Min Total Amount</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.minTotalAmount}</dd>
          <dt>
            <span id="minFirstInstallmentAmount">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.minFirstInstallmentAmount">
                Min First Installment Amount
              </Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.minFirstInstallmentAmount}</dd>
          <dt>
            <span id="creationFees">
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.creationFees">Creation Fees</Translate>
            </span>
          </dt>
          <dd>{installmentsPlanEntity.creationFees}</dd>
        </dl>
        <Button tag={Link} to="/installments-plan" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/installments-plan/${installmentsPlanEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InstallmentsPlanDetail;
