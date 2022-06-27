import { IRequestSystemFee } from 'app/shared/model/request-system-fee.model';

export interface IRequestSystemFeeDetails {
  id?: number;
  feeCode?: string | null;
  feeNameAr?: string | null;
  feeNameEn?: string | null;
  feeAmount?: number | null;
  requestSystemFee?: IRequestSystemFee | null;
}

export const defaultValue: Readonly<IRequestSystemFeeDetails> = {};
