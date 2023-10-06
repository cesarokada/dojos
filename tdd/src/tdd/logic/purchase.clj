(ns tdd.logic.purchase)

(defn- validate [{:keys [amount installments]}]
  (when (<= amount 0)
    (throw (IllegalArgumentException. "Purchase amount must be greater than 0")))
  (when (or (<= installments 0)
             (> installments 12))
    (throw (IllegalArgumentException. "Purchase installments must be greater than 0 and less than 12"))))

(defn create [purchase-order]
  (validate purchase-order))
