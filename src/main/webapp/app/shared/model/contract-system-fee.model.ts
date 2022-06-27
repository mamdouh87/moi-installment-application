import dayjs from 'dayjs';
import { IContractSystemFeeDetails } from 'app/shared/model/contract-system-fee-details.model';
import { IContract } from 'app/shared/model/contract.model';

export interface IContractSystemFee {
  id?: number;
  systemcode?: string | null;
  systemNameAr?: string | null;
  systemNameEn?: string | null;
  systemTotalFees?: number | null;
  status?: number | null;
  statusDate?: string | null;
  contractSystemFeeDetails?: IContractSystemFeeDetails[] | null;
  contract?: IContract | null;
}

export const defaultValue: Readonly<IContractSystemFee> = {};
