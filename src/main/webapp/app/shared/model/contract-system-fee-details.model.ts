import dayjs from 'dayjs';
import { IContractSystemFee } from 'app/shared/model/contract-system-fee.model';

export interface IContractSystemFeeDetails {
  id?: number;
  feeCode?: string | null;
  feeNameAr?: string | null;
  feeNameEn?: string | null;
  feeAmount?: number | null;
  status?: number | null;
  statusDate?: string | null;
  draftContractSystemFee?: number | null;
  contractSystemFee?: IContractSystemFee | null;
}

export const defaultValue: Readonly<IContractSystemFeeDetails> = {};
