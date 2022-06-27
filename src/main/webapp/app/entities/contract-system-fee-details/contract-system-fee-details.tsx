import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContractSystemFeeDetails } from 'app/shared/model/contract-system-fee-details.model';
import { getEntities } from './contract-system-fee-details.reducer';

export const ContractSystemFeeDetails = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const contractSystemFeeDetailsList = useAppSelector(state => state.contractSystemFeeDetails.entities);
  const loading = useAppSelector(state => state.contractSystemFeeDetails.loading);
  const totalItems = useAppSelector(state => state.contractSystemFeeDetails.totalItems);

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
      <h2 id="contract-system-fee-details-heading" data-cy="ContractSystemFeeDetailsHeading">
        <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.home.title">Contract System Fee Details</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/contract-system-fee-details/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.home.createLabel">
              Create new Contract System Fee Details
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {contractSystemFeeDetailsList && contractSystemFeeDetailsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feeCode')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.feeCode">Fee Code</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feeNameAr')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.feeNameAr">Fee Name Ar</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feeNameEn')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.feeNameEn">Fee Name En</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feeAmount')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.feeAmount">Fee Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('statusDate')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.statusDate">Status Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('draftContractSystemFee')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.draftContractSystemFee">
                    Draft Contract System Fee
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.contractSystemFee">
                    Contract System Fee
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contractSystemFeeDetailsList.map((contractSystemFeeDetails, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/contract-system-fee-details/${contractSystemFeeDetails.id}`} color="link" size="sm">
                      {contractSystemFeeDetails.id}
                    </Button>
                  </td>
                  <td>{contractSystemFeeDetails.feeCode}</td>
                  <td>{contractSystemFeeDetails.feeNameAr}</td>
                  <td>{contractSystemFeeDetails.feeNameEn}</td>
                  <td>{contractSystemFeeDetails.feeAmount}</td>
                  <td>{contractSystemFeeDetails.status}</td>
                  <td>
                    {contractSystemFeeDetails.statusDate ? (
                      <TextFormat type="date" value={contractSystemFeeDetails.statusDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{contractSystemFeeDetails.draftContractSystemFee}</td>
                  <td>
                    {contractSystemFeeDetails.contractSystemFee ? (
                      <Link to={`/contract-system-fee/${contractSystemFeeDetails.contractSystemFee.id}`}>
                        {contractSystemFeeDetails.contractSystemFee.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/contract-system-fee-details/${contractSystemFeeDetails.id}`}
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
                        to={`/contract-system-fee-details/${contractSystemFeeDetails.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/contract-system-fee-details/${contractSystemFeeDetails.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="moiInstallmentApplicationApp.contractSystemFeeDetails.home.notFound">
                No Contract System Fee Details found
              </Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={contractSystemFeeDetailsList && contractSystemFeeDetailsList.length > 0 ? '' : 'd-none'}>
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

export default ContractSystemFeeDetails;
