import { ICountry } from 'app/shared/model/country.model';
import { IRequest } from 'app/shared/model/request.model';

export interface ICustomer {
  id?: number;
  nationalId?: string | null;
  passportNo?: string | null;
  countryId?: number | null;
  mobileNo?: string | null;
  address?: string | null;
  fullName?: string | null;
  tradeLicense?: string | null;
  country?: ICountry | null;
  requests?: IRequest[] | null;
}

export const defaultValue: Readonly<ICustomer> = {};
