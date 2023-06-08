import { categoryI } from "./category.interface";

export interface FlowerI {
    id: string;
    name: string;
    color: string;
    price: string;
    category: categoryI;
    photo: string;
    family: string;
    stock: number;
    count: number;
}