CREATE TABLE "payment_coupons" (
	payment_id uuid NOT NULL,
	coupon_id SERIAL NOT NULL,
	CONSTRAINT payment_coupons_payments_payment_id_fkey FOREIGN KEY (payment_id) REFERENCES public.payments(id),
	CONSTRAINT payment_coupons_coupons_coupon_id_fkey FOREIGN KEY (coupon_id) REFERENCES public.coupons(id)
);