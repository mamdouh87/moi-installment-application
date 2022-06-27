import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContract } from 'app/shared/model/contract.model';
import { getEntities } from './contract.reducer';

export const Contract = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const contractList = useAppSelector(state => state.contract.entities);
  const loading = useAppSelector(state => state.contract.loading);
  const totalItems = useAppSelector(state => state.contract.totalItems);

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
      <h2 id="contract-heading" data-cy="ContractHeading">
        <Translate contentKey="moiInstallmentApplicationApp.contract.home.title">Contracts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="moiInstallmentApplicationApp.contract.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/contract/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="moiInstallmentApplicationApp.contract.home.createLabel">Create new Contract</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {contractList && contractList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('contractNo')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.contractNo">Contract No</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mobileNo')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.mobileNo">Mobile No</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('address')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.address">Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fullName')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.fullName">Full Name</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerId')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.customerId">Customer Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nationalId')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.nationalId">National Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('passportNo')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.passportNo">Passport No</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('countryId')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.countryId">Country Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tradeLicense')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.tradeLicense">Trade License</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('signDate')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.signDate">Sign Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('userId')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.userId">User Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('actualContractedAmount')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.actualContractedAmount">Actual Contracted Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('interestPercentage')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.interestPercentage">Interest Percentage</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('contractAmount')}>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.contractAmount">Contract Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.trafficContract">Traffic Contract</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="moiInstallmentApplicationApp.contract.installmentPlan">Installment Plan</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contractList.map((contract, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/contract/${contract.id}`} color="link" size="sm">
                      {contract.id}
                    </Button>
                  </td>
                  <td>{contract.contractNo}</td>
                  <td>{contract.status}</td>
                  <td>{contract.mobileNo}</td>
                  <td>{contract.address}</td>
                  <td>{contract.fullName}</td>
                  <td>{contract.customerId}</td>
                  <td>{contract.nationalId}</td>
                  <td>{contract.passportNo}</td>
                  <td>{contract.countryId}</td>
                  <td>{contract.tradeLicense}</td>
                  <td>{contract.signDate ? <TextFormat type="date" value={contract.signDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{contract.userId}</td>
                  <td>{contract.actualContractedAmount}</td>
                  <td>{contract.interestPercentage}</td>
                  <td>{contract.contractAmount}</td>
                  <td>
                    {contract.trafficContract ? (
                      <Link to={`/traffic-contract/${contract.trafficContract.id}`}>{contract.trafficContract.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {contract.installmentPlan ? (
                      <Link to={`/installments-plan/${contract.installmentPlan.id}`}>{contract.installmentPlan.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/contract/${contract.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/contract/${contract.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/contract/${contract.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="moiInstallmentApplicationApp.contract.home.notFound">No Contracts found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={contractList && contractList.length > 0 ? '' : 'd-none'}>
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

export default Contract;
