package com.company;

import com.company.strategy.Bronze;
import com.company.strategy.Gold;
import com.company.strategy.Silver;
import com.company.strategy.Strategy;

public enum ProductState {
    IN_STOCK{
        @Override
        public void startSale(Product p) throws Exception {
            p.setState(ProductState.FOR_SALE);
            System.out.printf("[%s] - Товар был выставлен на торги\n", p.getId());
        }

        @Override
        public void risePrice(Product p) throws Exception {
            throw new Exception("Товар еще не учавствует в торгах");
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
        private final CodeGenerator generator = new CodeGenerator();
        @Override
        public void startSale(Product p) throws Exception {
            throw new Exception("товар уже участвует в торгах");
        }

        @Override
        public void risePrice(Product p) throws Exception {
            p.setFinalPrice(p.getFinalPrice()+10);
            System.out.printf("[%s] - Цена на товар поднялась на 10 пунктов\n", p.getId());
        }

        @Override
        public void withdraw(Product p) throws Exception {
            if (p.getFinalPrice() == 0) {
                p.setState(ProductState.IN_STOCK);
                System.out.printf("[%s] - Товар был возвращен на склад\n", p.getId());
            } else {
                throw new Exception("товар уже в резерве, можно только выдать");
            }
        }

        @Override
        public void giveToTheWinner(Product p) throws Exception {
            Strategy s;
            if (p.getFinalPrice() > 0) {
                if (p.getFinalPrice() + p.getStartPrice() >= 100) {
                    s = new Gold();
                    p.setHonorary_code("Gold-"+s.getStatus(p.getId()));
                }
                if ((p.getFinalPrice() + p.getStartPrice()) >= 500 && (p.getFinalPrice() + p.getStartPrice()) < 1000) {
                    s = new Silver();
                    p.setHonorary_code("Silver-"+s.getStatus(p.getId()));
                }
                if (p.getFinalPrice() + p.getStartPrice() < 500) {
                    s = new Bronze();
                    p.setHonorary_code("Bronze-"+s.getStatus(p.getId()));
                }

                p.setState(ProductState.SOLD);
                System.out.printf("[%s] - Товар продан на торгах за %s\n", p.getId(), p.getFinalPrice()+p.getStartPrice());
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
