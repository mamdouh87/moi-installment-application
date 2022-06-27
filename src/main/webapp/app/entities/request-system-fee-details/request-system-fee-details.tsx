import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRequestSystemFeeDetails } from 'app/shared/model/request-system-fee-details.model';
import { getEntities } from './request-system-fee-details.reducer';

export const RequestSystemFeeDetails = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const requestSystemFeeDetailsList = useAppSelector(state => state.requestSystemFeeDetails.entities);
  const loading = useAppSelector(state => state.requestSystemFeeDetails.loading);
  const totalItems = useAppSelector(state => state.requestSystemFeeDetails.totalItems);

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
      <h2 id="request-system-fee-details-heading" data-cy="RequestSystemFeeDetailsHeading">
        <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.home.title">Request System Fee Details</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/request-system-fee-details/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.home.createLabel">
              Create new Request System Fee Details
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {requestSystemFeeDetailsList && requestSystemFeeDetailsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feeCode')}>
                  <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.feeCode">Fee Code</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feeNameAr')}>
                  <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.feeNameAr">Fee Name Ar</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feeNameEn')}>
                  <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.feeNameEn">Fee Name En</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feeAmount')}>
                  <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.feeAmount">Fee Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.requestSystemFee">
                    Request System Fee
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {requestSystemFeeDetailsList.map((requestSystemFeeDetails, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/request-system-fee-details/${requestSystemFeeDetails.id}`} color="link" size="sm">
                      {requestSystemFeeDetails.id}
                    </Button>
                  </td>
                  <td>{requestSystemFeeDetails.feeCode}</td>
                  <td>{requestSystemFeeDetails.feeNameAr}</td>
                  <td>{requestSystemFeeDetails.feeNameEn}</td>
                  <td>{requestSystemFeeDetails.feeAmount}</td>
                  <td>
                    {requestSystemFeeDetails.requestSystemFee ? (
                      <Link to={`/request-system-fee/${requestSystemFeeDetails.requestSystemFee.id}`}>
                        {requestSystemFeeDetails.requestSystemFee.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/request-system-fee-details/${requestSystemFeeDetails.id}`}
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
                        to={`/request-system-fee-details/${requestSystemFeeDetails.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/request-system-fee-details/${requestSystemFeeDetails.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="moiInstallmentApplicationApp.requestSystemFeeDetails.home.notFound">
                No Request System Fee Details found
              </Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={requestSystemFeeDetailsList && requestSystemFeeDetailsList.length > 0 ? '' : 'd-none'}>
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

export default RequestSystemFeeDetails;
