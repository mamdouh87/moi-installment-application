import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contract-payment.reducer';

export const ContractPaymentDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractPaymentEntity = useAppSelector(state => state.contractPayment.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractPaymentDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.contractPayment.detail.title">ContractPayment</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contractPaymentEntity.id}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.status">Status</Translate>
            </span>
          </dt>
          <dd>{contractPaymentEntity.status}</dd>
          <dt>
            <span id="installmentNo">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.installmentNo">Installment No</Translate>
            </span>
          </dt>
          <dd>{contractPaymentEntity.installmentNo}</dd>
          <dt>
            <span id="installmentAmount">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.installmentAmount">Installment Amount</Translate>
            </span>
          </dt>
          <dd>{contractPaymentEntity.installmentAmount}</dd>
          <dt>
            <span id="installmentDate">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.installmentDate">Installment Date</Translate>
            </span>
          </dt>
          <dd>
            {contractPaymentEntity.installmentDate ? (
              <TextFormat value={contractPaymentEntity.installmentDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="installmentLateFees">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.installmentLateFees">Installment Late Fees</Translate>
            </span>
          </dt>
          <dd>{contractPaymentEntity.installmentLateFees}</dd>
          <dt>
            <span id="paymentDate">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.paymentDate">Payment Date</Translate>
            </span>
          </dt>
          <dd>
            {contractPaymentEntity.paymentDate ? (
              <TextFormat value={contractPaymentEntity.paymentDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="paymentType">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.paymentType">Payment Type</Translate>
            </span>
          </dt>
          <dd>{contractPaymentEntity.paymentType}</dd>
          <dt>
            <span id="receiptNo">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.receiptNo">Receipt No</Translate>
            </span>
          </dt>
          <dd>{contractPaymentEntity.receiptNo}</dd>
          <dt>
            <span id="creationFees">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.creationFees">Creation Fees</Translate>
            </span>
          </dt>
          <dd>{contractPaymentEntity.creationFees}</dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.contractPayment.contract">Contract</Translate>
          </dt>
          <dd>{contractPaymentEntity.contract ? contractPaymentEntity.contract.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/contract-payment" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract-payment/${contractPaymentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractPaymentDetail;
