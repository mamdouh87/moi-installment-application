import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contract-system-fee.reducer';

export const ContractSystemFeeDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractSystemFeeEntity = useAppSelector(state => state.contractSystemFee.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractSystemFeeDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.detail.title">ContractSystemFee</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeEntity.id}</dd>
          <dt>
            <span id="systemcode">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.systemcode">Systemcode</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeEntity.systemcode}</dd>
          <dt>
            <span id="systemNameAr">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.systemNameAr">System Name Ar</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeEntity.systemNameAr}</dd>
          <dt>
            <span id="systemNameEn">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.systemNameEn">System Name En</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeEntity.systemNameEn}</dd>
          <dt>
            <span id="systemTotalFees">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.systemTotalFees">System Total Fees</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeEntity.systemTotalFees}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.status">Status</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeEntity.status}</dd>
          <dt>
            <span id="statusDate">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.statusDate">Status Date</Translate>
            </span>
          </dt>
          <dd>
            {contractSystemFeeEntity.statusDate ? (
              <TextFormat value={contractSystemFeeEntity.statusDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.contractSystemFee.contract">Contract</Translate>
          </dt>
          <dd>{contractSystemFeeEntity.contract ? contractSystemFeeEntity.contract.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/contract-system-fee" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract-system-fee/${contractSystemFeeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractSystemFeeDetail;
