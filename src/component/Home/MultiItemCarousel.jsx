import React from 'react'

import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from 'react-slick';
import { topMeals } from './topMeels';
import CarouselItem from './CarouseItem';

const MultiItemCarousel =() =>
{
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 5,
        slidesToScroll: 1,
        autoplay:true,
        autoplaySpeed:2000,
        arrows:false,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                },
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 2,
                },
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                },
            },
        ],

      };
    return(
        <div className="w-full">
            <Slider {...settings}>
                {topMeals.map((item)=>(<CarouselItem 
                image={item.image} 
                title={item.title}
                />))}
            </Slider>
        </div>
    )
}
export default MultiItemCarousel