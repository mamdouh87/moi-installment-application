import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contract-system-fee-details.reducer';

export const ContractSystemFeeDetailsDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractSystemFeeDetailsEntity = useAppSelector(state => state.contractSystemFeeDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractSystemFeeDetailsDetailsHeading">
          <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.detail.title">ContractSystemFeeDetails</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeDetailsEntity.id}</dd>
          <dt>
            <span id="feeCode">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.feeCode">Fee Code</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeDetailsEntity.feeCode}</dd>
          <dt>
            <span id="feeNameAr">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.feeNameAr">Fee Name Ar</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeDetailsEntity.feeNameAr}</dd>
          <dt>
            <span id="feeNameEn">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.feeNameEn">Fee Name En</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeDetailsEntity.feeNameEn}</dd>
          <dt>
            <span id="feeAmount">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.feeAmount">Fee Amount</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeDetailsEntity.feeAmount}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.status">Status</Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeDetailsEntity.status}</dd>
          <dt>
            <span id="statusDate">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.statusDate">Status Date</Translate>
            </span>
          </dt>
          <dd>
            {contractSystemFeeDetailsEntity.statusDate ? (
              <TextFormat value={contractSystemFeeDetailsEntity.statusDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="draftContractSystemFee">
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.draftContractSystemFee">
                Draft Contract System Fee
              </Translate>
            </span>
          </dt>
          <dd>{contractSystemFeeDetailsEntity.draftContractSystemFee}</dd>
          <dt>
            <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.contractSystemFee">Contract System Fee</Translate>
          </dt>
          <dd>{contractSystemFeeDetailsEntity.contractSystemFee ? contractSystemFeeDetailsEntity.contractSystemFee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/contract-system-fee-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract-system-fee-details/${contractSystemFeeDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractSystemFeeDetailsDetail;
