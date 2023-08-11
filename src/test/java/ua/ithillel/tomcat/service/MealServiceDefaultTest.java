package ua.ithillel.tomcat.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.ithillel.tomcat.client.MealClient;
import ua.ithillel.tomcat.dao.FavouriteMealDao;
import ua.ithillel.tomcat.exception.MealAppException;
import ua.ithillel.tomcat.model.dto.MealResponseDto;
import ua.ithillel.tomcat.model.entity.MealEntity;
import ua.ithillel.tomcat.model.mapper.MealMapper;
import ua.ithillel.tomcat.model.mapper.MealMapperDefault;
import ua.ithillel.tomcat.model.vm.MealItemVm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class MealServiceDefaultTest {
    private MealSearchService mealSearchService;

    @Mock
    private MealClient mealClientMock;
    @Mock
    private FavouriteMealDao favouriteMealDaoMock;
    private MealMapper mealMapper = new MealMapperDefault();

    private MealResponseDto mockSearchResponse;
    private List<MealEntity> mockMealDaoData;

    @BeforeEach
    public void setUp() {
        // dependencies: client and dao
        initializeMockResponse();
        initializeDaoMockData();

        openMocks(this);

//        mealClientMock = mock();
//        favouriteMealDaoMock = mock();

        mealSearchService = new MealSearchServiceDefault(mealClientMock, mealMapper, favouriteMealDaoMock);
    }

    @Test
    public void searchMealsTest_returnsListOfMeals() {
        System.out.println("Starint test");

        MealResponseDto mockResponse = new MealResponseDto();
        mockResponse.setMeals(new ArrayList<>());

        when(mealClientMock.searchMealByName(anyString())).thenReturn(mockResponse);

        String testName = "test name";

        List<MealItemVm> mealItemVms = mealSearchService.searchMeals(testName);

        assertNotNull(mealItemVms);
    }

    @Test
    public void searchMealsTest_returnsNonEmptyListOfMeals() {
        System.out.println("Starint test");


        when(mealClientMock.searchMealByName(anyString())).thenReturn(mockSearchResponse);

        String testName = "test name";

        List<MealItemVm> mealItemVms = mealSearchService.searchMeals(testName);

        assertNotNull(mealItemVms);
        assertNotEquals(mealItemVms.size(), 0);
    }

    @Test
    public void getFavouriteMealsTest_returnsListOfMeals() throws MealAppException {
        when(favouriteMealDaoMock.findAll(anyBoolean())).thenReturn(new ArrayList<>());

        List<MealItemVm> favouriteMeals = mealSearchService.getFavouriteMeals(true);

        assertNotNull(favouriteMeals);
    }

    @Test
    public void getFavouriteMealsTest_returnsNonEmptyListOfMeals() throws MealAppException {
        MealEntity mockMeal = new MealEntity();
        mockMeal.setName("mock name");
        mockMeal.setRecipe("Mock recipe");
        mockMeal.setImageUrl("Mock image");
        mockMeal.setIngredients(new ArrayList<>());

        List<MealEntity> mockMeals = List.of(mockMeal);

        when(favouriteMealDaoMock.findAll(anyBoolean())).thenReturn(mockMeals);

        List<MealItemVm> favouriteMeals = mealSearchService.getFavouriteMeals(true);

        assertNotNull(favouriteMeals);
        assertNotEquals(favouriteMeals.size(), 0);
    }

    @Test
    public void toggleFavouriteMeal_favouriteIsRemoved() throws MealAppException {
        MealEntity mealEntity = mockMealDaoData.get(0);
        when(favouriteMealDaoMock.findByMealId(anyString())).thenReturn(mealEntity);

        MealItemVm mockMeal = new MealItemVm();
        mockMeal.setName("mock name");
        mockMeal.setRecipe("Mock recipe");
        mockMeal.setImageUrl("Mock image");
        mockMeal.setIngredientVms(new ArrayList<>());
        mockMeal.setId("testId");

        mealSearchService.toggleFavouriteMeal(mockMeal);
    }

    @Test
    public void toggleFavouriteMeal_favouriteIsSaved() throws MealAppException {
        doNothing().when(favouriteMealDaoMock).save(any());

        MealItemVm mockMeal = new MealItemVm();
        mockMeal.setName("mock name");
        mockMeal.setRecipe("Mock recipe");
        mockMeal.setImageUrl("Mock image");
        mockMeal.setIngredientVms(new ArrayList<>());
        mockMeal.setId("testId");
        
        mealSearchService.toggleFavouriteMeal(mockMeal);
    }

    private void initializeMockResponse() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("mealclient-mock-response.json");
        try (InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
             BufferedReader br = new BufferedReader(inputStreamReader);
        ) {
            StringBuffer stringBuffer = new StringBuffer();
            br.lines().forEach(stringBuffer::append);

            String fileContent = stringBuffer.toString();

            ObjectMapper objectMapper = new ObjectMapper();
            mockSearchResponse = objectMapper.readValue(fileContent, MealResponseDto.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeDaoMockData() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("mealdao-mock-data.json");
        try (InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
             BufferedReader br = new BufferedReader(inputStreamReader);
        ) {
            StringBuffer stringBuffer = new StringBuffer();
            br.lines().forEach(stringBuffer::append);

            String fileContent = stringBuffer.toString();

            ObjectMapper objectMapper = new ObjectMapper();
            mockMealDaoData = objectMapper.readValue(fileContent, new TypeReference<List<MealEntity>>() {});

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
