import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInstallmentsPlan } from 'app/shared/model/installments-plan.model';
import { getEntities } from './installments-plan.reducer';

export const InstallmentsPlan = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const installmentsPlanList = useAppSelector(state => state.installmentsPlan.entities);
  const loading = useAppSelector(state => state.installmentsPlan.loading);
  const totalItems = useAppSelector(state => state.installmentsPlan.totalItems);

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
      <h2 id="installments-plan-heading" data-cy="InstallmentsPlanHeading">
        <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.home.title">Installments Plans</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/installments-plan/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.home.createLabel">Create new Installments Plan</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {installmentsPlanList && installmentsPlanList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nameAr')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.nameAr">Name Ar</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nameEn')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.nameEn">Name En</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('numberOfInstallments')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.numberOfInstallments">
                    Number Of Installments
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('installmentIntervalDays')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.installmentIntervalDays">
                    Installment Interval Days
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('interestRate')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.interestRate">Interest Rate</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('installmentGraceDays')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.installmentGraceDays">
                    Installment Grace Days
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dailyLatePercentage')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.dailyLatePercentage">
                    Daily Late Percentage
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dailyLateFee')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.dailyLateFee">Daily Late Fee</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('maxTotalAmount')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.maxTotalAmount">Max Total Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('minTotalAmount')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.minTotalAmount">Min Total Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('minFirstInstallmentAmount')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.minFirstInstallmentAmount">
                    Min First Installment Amount
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('creationFees')}>
                  <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.creationFees">Creation Fees</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {installmentsPlanList.map((installmentsPlan, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/installments-plan/${installmentsPlan.id}`} color="link" size="sm">
                      {installmentsPlan.id}
                    </Button>
                  </td>
                  <td>{installmentsPlan.status}</td>
                  <td>{installmentsPlan.nameAr}</td>
                  <td>{installmentsPlan.nameEn}</td>
                  <td>{installmentsPlan.numberOfInstallments}</td>
                  <td>{installmentsPlan.installmentIntervalDays}</td>
                  <td>{installmentsPlan.interestRate}</td>
                  <td>{installmentsPlan.installmentGraceDays}</td>
                  <td>{installmentsPlan.dailyLatePercentage}</td>
                  <td>{installmentsPlan.dailyLateFee}</td>
                  <td>{installmentsPlan.maxTotalAmount}</td>
                  <td>{installmentsPlan.minTotalAmount}</td>
                  <td>{installmentsPlan.minFirstInstallmentAmount}</td>
                  <td>{installmentsPlan.creationFees}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/installments-plan/${installmentsPlan.id}`}
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
                        to={`/installments-plan/${installmentsPlan.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/installments-plan/${installmentsPlan.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="moiInstallmentApplicationApp.installmentsPlan.home.notFound">No Installments Plans found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={installmentsPlanList && installmentsPlanList.length > 0 ? '' : 'd-none'}>
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

export default InstallmentsPlan;
