import { IRequestSystemFee } from 'app/shared/model/request-system-fee.model';
import { ICustomer } from 'app/shared/model/customer.model';

export interface IRequest {
  id?: number;
  requestNo?: string | null;
  requestServiceCode?: string | null;
  requestDescription?: string | null;
  vehicleTypeId?: number | null;
  licenseTypeId?: number | null;
  plateNo?: string | null;
  motorNo?: string | null;
  chassisNo?: string | null;
  trafficUnitCode?: string | null;
  requestsystemFees?: IRequestSystemFee[] | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IRequest> = {};
