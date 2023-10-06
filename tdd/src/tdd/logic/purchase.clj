(ns tdd.logic.purchase)

(defn- discountable? [{:keys [installments]}]
  (= installments 1))

(defn round-half-up-2-decimal-places [x]
  (let [x (* 100 x)
        rounded (Math/round x)]
    (/ rounded 100.0)))

(defn- apply-discount [purchase-order]
  (if (discountable? purchase-order)
    (update purchase-order :amount #(round-half-up-2-decimal-places (* % 0.95)))
    purchase-order))

(defn- validate [{:keys [amount installments] :as purchase-order}]
  (cond
    (<= amount 0)
    (throw (IllegalArgumentException. "Purchase amount must be greater than 0"))
    (<= installments 0)
    (throw (IllegalArgumentException. "Purchase installments must be greater than 0"))
    (> installments 12)
    (throw (IllegalArgumentException. "Purchase installments must be less than 13"))
    (and (< amount 100) (> installments 1))
    (throw (IllegalArgumentException. "Purchase with amount less than 100 must has 1 installment"))
    :else purchase-order))

(defn create [purchase-order]
  (-> purchase-order
      validate
      apply-discount))
