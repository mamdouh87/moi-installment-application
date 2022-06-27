import dayjs from 'dayjs';
import { ITrafficContract } from 'app/shared/model/traffic-contract.model';
import { IInstallmentsPlan } from 'app/shared/model/installments-plan.model';
import { IContractSystemFee } from 'app/shared/model/contract-system-fee.model';
import { IContractPayment } from 'app/shared/model/contract-payment.model';

export interface IContract {
  id?: number;
  contractNo?: string | null;
  status?: number | null;
  mobileNo?: string | null;
  address?: string | null;
  fullName?: string | null;
  customerId?: number | null;
  nationalId?: string | null;
  passportNo?: string | null;
  countryId?: number | null;
  tradeLicense?: string | null;
  signDate?: string | null;
  userId?: number | null;
  actualContractedAmount?: number | null;
  interestPercentage?: number | null;
  contractAmount?: number | null;
  trafficContract?: ITrafficContract | null;
  installmentPlan?: IInstallmentsPlan | null;
  contractSystemFees?: IContractSystemFee[] | null;
  contractPayments?: IContractPayment[] | null;
}

export const defaultValue: Readonly<IContract> = {};
