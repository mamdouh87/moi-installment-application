import dayjs from 'dayjs';
import { IContract } from 'app/shared/model/contract.model';

export interface IContractPayment {
  id?: number;
  status?: number | null;
  installmentNo?: number | null;
  installmentAmount?: number | null;
  installmentDate?: string | null;
  installmentLateFees?: number | null;
  paymentDate?: string | null;
  paymentType?: number | null;
  receiptNo?: string | null;
  creationFees?: number | null;
  contract?: IContract | null;
}

export const defaultValue: Readonly<IContractPayment> = {};
