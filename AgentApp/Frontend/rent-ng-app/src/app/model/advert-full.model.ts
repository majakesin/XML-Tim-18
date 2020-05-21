import { Car } from './car.model';

export interface AdvertFullModel {

    advertId: number;
    profileImage: string;
    galeryImages: string[];
    price: number;
    grade: number;
    ownerEmail: string;
    firmName: string;
    description: string;

    car: Car


}