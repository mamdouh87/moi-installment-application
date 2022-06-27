export interface IInstallmentsPlan {
  id?: number;
  status?: number | null;
  nameAr?: string | null;
  nameEn?: string | null;
  numberOfInstallments?: number | null;
  installmentIntervalDays?: number | null;
  interestRate?: number | null;
  installmentGraceDays?: number | null;
  dailyLatePercentage?: number | null;
  dailyLateFee?: number | null;
  maxTotalAmount?: number | null;
  minTotalAmount?: number | null;
  minFirstInstallmentAmount?: number | null;
  creationFees?: number | null;
}

export const defaultValue: Readonly<IInstallmentsPlan> = {};
