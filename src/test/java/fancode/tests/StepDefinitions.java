package fancode.tests;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import fancode.apiendpoints.ApiClient;
import fancode.apiendpoints.models.Todo;
import fancode.apiendpoints.models.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class StepDefinitions {
	private List<User> users;
	private List<Todo> todos;
	private List<User> fanCodeUsers;
	private static Logger log = LogManager.getLogger("StepDefinitions");

	@Given("User has the todo tasks")
	public void user_has_the_todo_tasks() throws Exception {
		log.info("Fetching todo tasks from the API...");
		Response todosResponse = ApiClient.getTodos();
		ObjectMapper mapper = new ObjectMapper();
		todos = mapper.readValue(todosResponse.asString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Todo.class));
		log.info("Fetched {} todo tasks.", todos.size());
	}

	@And("User belongs to the city FanCode")
	public void user_belongs_to_the_city_fancode() throws Exception {
		log.info("Fetching user data from the API...");
		Response usersResponse = ApiClient.getUsers();
		ObjectMapper mapper = new ObjectMapper();
		users = mapper.readValue(usersResponse.asString(),
				mapper.getTypeFactory().constructCollectionType(List.class, User.class));

		// Filter FanCode users based on latitude and longitude
		fanCodeUsers = users.stream()
				.filter(user -> {
					double lat = Double.parseDouble(user.getAddress().getGeo().getLat());
					double lng = Double.parseDouble(user.getAddress().getGeo().getLng());
					return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
				})
				.collect(Collectors.toList());
		log.info("Filtered users from FanCode city: {} users found.", fanCodeUsers.size());
	}

	@Then("User Completed task percentage should be greater than {int}%")
	public void user_completed_task_percentage_should_be_greater_than(int percentage) {
		log.info("Validating completed task percentage for FanCode users...");
		for (User user : fanCodeUsers) {
			List<Todo> userTodos = todos.stream()
					.filter(todo -> todo.getUserId() == user.getId())
					.collect(Collectors.toList());

			long completedCount = userTodos.stream().filter(Todo::isCompleted).count();
			double completionPercentage = (completedCount * 100.0) / userTodos.size();

			Assert.assertTrue(completionPercentage > percentage, 
					"User " + user.getName() + " has less than " + percentage + "% tasks completed.");
		}
		log.info("Task completion validation completed successfully.");
	}
}
