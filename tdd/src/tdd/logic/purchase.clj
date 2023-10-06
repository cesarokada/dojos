(ns tdd.logic.purchase)

(defn- validate [{:keys [amount installments]}]
  (cond
    (<= amount 0)
    (throw (IllegalArgumentException. "Purchase amount must be greater than 0"))
    (<= installments 0)
    (throw (IllegalArgumentException. "Purchase installments must be greater than 0"))
    (> installments 12)
    (throw (IllegalArgumentException. "Purchase installments must be less than 13"))
    (and (< amount 100) (> installments 1))
    (throw (IllegalArgumentException. "Purchase with amount less than 100 must has 1 installment"))))

(defn create [purchase-order]
  (validate purchase-order))
