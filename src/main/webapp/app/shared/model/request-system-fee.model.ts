import { IRequestSystemFeeDetails } from 'app/shared/model/request-system-fee-details.model';
import { IRequest } from 'app/shared/model/request.model';

export interface IRequestSystemFee {
  id?: number;
  systemcode?: string | null;
  systemNameAr?: string | null;
  systemNameEn?: string | null;
  systemTotalFees?: number | null;
  requestSystemFeeDetails?: IRequestSystemFeeDetails[] | null;
  request?: IRequest | null;
}

export const defaultValue: Readonly<IRequestSystemFee> = {};
