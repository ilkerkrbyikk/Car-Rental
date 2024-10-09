package com.Ilker.service.car;

import com.Ilker.entitiy.*;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.repository.*;
import com.Ilker.request.AddCarRequest;
import com.Ilker.request.AddRatingRequest;
import com.Ilker.request.AddReviewRequest;
import com.Ilker.request.UpdateCarRequest;
import com.Ilker.service.brand.BrandService;
import com.Ilker.service.brand_model.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final BrandService brandService;
    private final ModelService modelService;
    private final ColorRepository colorRepository;
    private final FuelRepository fuelRepository;
    private final GearRepository gearRepository;
    private final ReviewRepository reviewRepository;
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    @Override
    public List<Car> getAllCars() {

        return carRepository.findAll();
    }

    @Override
    public List<Car> getAvailableCars(){

        return carRepository.findByAvailable(true);
    }


      //? Lazım olursa duruma göre açarız.
    @Override
    public void updateAvailable(Long carId, boolean available){

        //checkIsExistsByCarId(carId);
        Car car = carRepository.findById(carId).orElse(null);
        if(car != null){
            car.setAvailable(available);
            carRepository.save(car);
        }

    }

//    public void updateNotAvailable(Long carId,boolean available){
//        checkIsExistsByCarId(carId);
//        Car car = carRepository.findById(carId).orElse(null);
//        if(car != null){
//            car.setAvailable(available);
//            carRepository.save(car);
//        }
//    }

    @Override
    public Car add(AddCarRequest request) {
         brandService.checkIsExistsByBrandId(request.getBrandId());
         modelService.checkIsExistsByModelId(request.getModelId());

         Brand brand = brandRepository.getBrandById(request.getBrandId());
         Model model = modelRepository.getModelById(request.getModelId());
         Color color = colorRepository.getColorById(request.getColorId());
         Fuel fuel = fuelRepository.getFuelById(request.getFuelId());
         Gear gear = gearRepository.getGearById(request.getGearId());

         Car car = new Car();
         car.setBrand(brand);
         car.setModel(model);
         car.setAvailable(true);
         car.setFuel(fuel);
         car.setGear(gear);
         car.setColor(color);
         car.setKilometer(request.getKilometer());
         car.setDailyPrice(request.getDailyPrice());
         car.setModelYear(request.getModelYear());
            return carRepository.save(car);
    }

    @Override
    public Car update(UpdateCarRequest request, Long carId) {
        brandService.checkIsExistsByBrandId(request.getBrandId());
        modelService.checkIsExistsByModelId(request.getModelId());
        checkIsExistsByCarId(carId);

        Car car = carRepository.getCarById(carId);

        Brand brand = brandRepository.getBrandById(request.getBrandId());
        Model model = modelRepository.getModelById(request.getModelId());
        Fuel fuel = fuelRepository.getFuelById(request.getFuelId());
        Gear gear = gearRepository.getGearById(request.getGearId());
        Color color = colorRepository.getColorById(request.getColorId());

        car.setBrand(brand);
        car.setModel(model);
        car.setFuel(fuel);
        car.setGear(gear);
        car.setColor(color);
        car.setKilometer(request.getKilometer());
        car.setDailyPrice(request.getDailyPrice());
        car.setModelYear(request.getModelYear());

        return carRepository.save(car);
    }

    @Override
    public void delete(Long id) {
        checkIsExistsByCarId(id);

        carRepository.deleteById(id);

    }

    @Override
    public void checkIsExistsByCarId(Long id){
        if(carRepository.existsById(id)){
            throw new ResourceNotFoundException("Car is not found.");
        }
    }

    public double getAverageRating(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found."));

        List<Rating> ratings = car.getRatings();
        return ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);
    }

    public void addReview(Long carId, AddReviewRequest request)throws UsernameNotFoundException, ResourceNotFoundException {
        checkIsExistsByCarId(carId);
        Car car = carRepository.findById(carId).orElse(null);
        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(()-> new UsernameNotFoundException("CUSTOMER NOT FOUND."));

        //TODO: Önceden review yapmış mı kontrol et.

        Review review = new Review();
        review.setMessage(request.getMessage());
        review.setUser(user);
        review.setCar(car);
        reviewRepository.save(review);
    }

    public void addRating(AddRatingRequest request, Long carId){
        checkIsExistsByCarId(carId);
        Car car = carRepository.findById(carId).orElse(null);

        //TODO: Önceden rating atmış mı kontrol et.

        Rating rating = new Rating();
        rating.setScore(request.getScore());
        rating.setCar(car);
        ratingRepository.save(rating);
    }

}
