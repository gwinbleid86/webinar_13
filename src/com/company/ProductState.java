package com.company;

public enum ProductState {
    IN_STOCK{
        @Override
        public void startSale(Product p) throws Exception {
            throw new Exception("Товар еще не учавствует в торгах");
        }

        @Override
        public void risePrice(Product p) throws Exception {
            p.setState(ProductState.FOR_SALE);
            System.out.println("Товар был выставлен на торги");
        }

        @Override
        public void withdraw(Product p) throws Exception {
            throw new Exception("нельзя отдать товар сразу со склада");
        }

        @Override
        public void giveToTheWinner(Product p) throws Exception {
            throw new Exception("нельзя снять с торгов товар, который в них не участвует");
        }
    },
    FOR_SALE {
        @Override
        public void startSale(Product p) throws Exception {
            throw new Exception("товар уже участвует в торгах");
        }

        @Override
        public void risePrice(Product p) throws Exception {
            p.setFinalPrice(p.getFinalPrice()+10);
            System.out.println("Цена на товар поднялась на 10 пунктов");
        }

        @Override
        public void withdraw(Product p) throws Exception {
            if (p.getFinalPrice() == 0) {
                p.setState(ProductState.IN_STOCK);
            } else {
                throw new Exception("товар уже в резерве, можно только выдать");
            }
        }

        @Override
        public void giveToTheWinner(Product p) throws Exception {
            if (p.getFinalPrice() > 0) {
                p.setState(ProductState.SOLD);
                System.out.println("Товар продан на торгах");
            } else {
                throw new Exception("нельзя отдать товар бесплатно");
            }
        }
    },
    SOLD {
        @Override
        public void startSale(Product p) throws Exception {
            throw new Exception("нельзя повысить стоимость, так как товар уже продан");
        }

        @Override
        public void risePrice(Product p) throws Exception {
            throw new Exception("нельзя начать продажу, так как товар уже продан");
        }

        @Override
        public void withdraw(Product p) throws Exception {
            throw new Exception("нельзя снять с торгов, так как товар уже продан");
        }

        @Override
        public void giveToTheWinner(Product p) throws Exception {
            throw new Exception("нельзя выдать покупателю, так как товар уже выдан");
        }
    };

    public abstract void startSale(Product p) throws Exception;
    public abstract void risePrice(Product p) throws Exception;
    public abstract void withdraw(Product p) throws Exception;
    public abstract void giveToTheWinner(Product p) throws Exception;
}
