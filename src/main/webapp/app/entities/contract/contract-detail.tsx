import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contract.reducer';

export const ContractDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractEntity = useAppSelector(state => state.contract.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.contract.detail.title">Contract</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contractEntity.id}</dd>
          <dt>
            <span id="contractNo">
              <Translate contentKey="moiInstallmentApplicationApp.contract.contractNo">Contract No</Translate>
            </span>
          </dt>
          <dd>{contractEntity.contractNo}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="moiInstallmentApplicationApp.contract.status">Status</Translate>
            </span>
          </dt>
          <dd>{contractEntity.status}</dd>
          <dt>
            <span id="mobileNo">
              <Translate contentKey="moiInstallmentApplicationApp.contract.mobileNo">Mobile No</Translate>
            </span>
          </dt>
          <dd>{contractEntity.mobileNo}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="moiInstallmentApplicationApp.contract.address">Address</Translate>
            </span>
          </dt>
          <dd>{contractEntity.address}</dd>
          <dt>
            <span id="fullName">
              <Translate contentKey="moiInstallmentApplicationApp.contract.fullName">Full Name</Translate>
            </span>
          </dt>
          <dd>{contractEntity.fullName}</dd>
          <dt>
            <span id="customerId">
              <Translate contentKey="moiInstallmentApplicationApp.contract.customerId">Customer Id</Translate>
            </span>
          </dt>
          <dd>{contractEntity.customerId}</dd>
          <dt>
            <span id="nationalId">
              <Translate contentKey="moiInstallmentApplicationApp.contract.nationalId">National Id</Translate>
            </span>
          </dt>
          <dd>{contractEntity.nationalId}</dd>
          <dt>
            <span id="passportNo">
              <Translate contentKey="moiInstallmentApplicationApp.contract.passportNo">Passport No</Translate>
            </span>
          </dt>
          <dd>{contractEntity.passportNo}</dd>
          <dt>
            <span id="countryId">
              <Translate contentKey="moiInstallmentApplicationApp.contract.countryId">Country Id</Translate>
            </span>
          </dt>
          <dd>{contractEntity.countryId}</dd>
          <dt>
            <span id="tradeLicense">
              <Translate contentKey="moiInstallmentApplicationApp.contract.tradeLicense">Trade License</Translate>
            </span>
          </dt>
          <dd>{contractEntity.tradeLicense}</dd>
          <dt>
            <span id="signDate">
              <Translate contentKey="moiInstallmentApplicationApp.contract.signDate">Sign Date</Translate>
            </span>
          </dt>
          <dd>{contractEntity.signDate ? <TextFormat value={contractEntity.signDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="userId">
              <Translate contentKey="moiInstallmentApplicationApp.contract.userId">User Id</Translate>
            </span>
          </dt>
          <dd>{contractEntity.userId}</dd>
          <dt>
            <span id="actualContractedAmount">
              <Translate contentKey="moiInstallmentApplicationApp.contract.actualContractedAmount">Actual Contracted Amount</Translate>
            </span>
          </dt>
          <dd>{contractEntity.actualContractedAmount}</dd>
          <dt>
            <span id="interestPercentage">
              <Translate contentKey="moiInstallmentApplicationApp.contract.interestPercentage">Interest Percentage</Translate>
            </span>
          </dt>
          <dd>{contractEntity.interestPercentage}</dd>
          <dt>
            <span id="contractAmount">
              <Translate contentKey="moiInstallmentApplicationApp.contract.contractAmount">Contract Amount</Translate>
            </span>
          </dt>
          <dd>{contractEntity.contractAmount}</dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.contract.trafficContract">Traffic Contract</Translate>
          </dt>
          <dd>{contractEntity.trafficContract ? contractEntity.trafficContract.id : ''}</dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.contract.installmentPlan">Installment Plan</Translate>
          </dt>
          <dd>{contractEntity.installmentPlan ? contractEntity.installmentPlan.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/contract" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract/${contractEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractDetail;
