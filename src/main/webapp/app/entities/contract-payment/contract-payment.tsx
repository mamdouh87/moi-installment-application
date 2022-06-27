import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContractPayment } from 'app/shared/model/contract-payment.model';
import { getEntities } from './contract-payment.reducer';

export const ContractPayment = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const contractPaymentList = useAppSelector(state => state.contractPayment.entities);
  const loading = useAppSelector(state => state.contractPayment.loading);
  const totalItems = useAppSelector(state => state.contractPayment.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { match } = props;

  return (
    <div>
      <h2 id="contract-payment-heading" data-cy="ContractPaymentHeading">
        <Translate contentKey="moiInstallmentApplicationApp.contractPayment.home.title">Contract Payments</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="moiInstallmentApplicationApp.contractPayment.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/contract-payment/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="moiInstallmentApplicationApp.contractPayment.home.createLabel">Create new Contract Payment</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {contractPaymentList && contractPaymentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('installmentNo')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.installmentNo">Installment No</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('installmentAmount')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.installmentAmount">Installment Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('installmentDate')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.installmentDate">Installment Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('installmentLateFees')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.installmentLateFees">Installment Late Fees</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('paymentDate')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.paymentDate">Payment Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('paymentType')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.paymentType">Payment Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('receiptNo')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.receiptNo">Receipt No</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('creationFees')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.creationFees">Creation Fees</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="moiInstallmentApplicationApp.contractPayment.contract">Contract</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contractPaymentList.map((contractPayment, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/contract-payment/${contractPayment.id}`} color="link" size="sm">
                      {contractPayment.id}
                    </Button>
                  </td>
                  <td>{contractPayment.status}</td>
                  <td>{contractPayment.installmentNo}</td>
                  <td>{contractPayment.installmentAmount}</td>
                  <td>
                    {contractPayment.installmentDate ? (
                      <TextFormat type="date" value={contractPayment.installmentDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{contractPayment.installmentLateFees}</td>
                  <td>
                    {contractPayment.paymentDate ? (
                      <TextFormat type="date" value={contractPayment.paymentDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{contractPayment.paymentType}</td>
                  <td>{contractPayment.receiptNo}</td>
                  <td>{contractPayment.creationFees}</td>
                  <td>
                    {contractPayment.contract ? (
                      <Link to={`/contract/${contractPayment.contract.id}`}>{contractPayment.contract.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/contract-payment/${contractPayment.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/contract-payment/${contractPayment.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/contract-payment/${contractPayment.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="moiInstallmentApplicationApp.contractPayment.home.notFound">No Contract Payments found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={contractPaymentList && contractPaymentList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default ContractPayment;
