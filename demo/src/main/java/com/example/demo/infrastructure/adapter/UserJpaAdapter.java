package com.example.demo.infrastructure.adapter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.demo.domain.data.UserDto;
import com.example.demo.domain.data.UserRequestBody;
import com.example.demo.domain.spi.UserPersistancePort;
import com.example.demo.infrastructure.entity.Users;
import com.example.demo.infrastructure.exceptions.EmailExists;
import com.example.demo.infrastructure.exceptions.InvalidRole;
import com.example.demo.infrastructure.mapper.UserMapper;
import com.example.demo.infrastructure.repository.UsersRepository;

@Service
public class UserJpaAdapter implements UserPersistancePort{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public List<UserDto> getAllUsers(int page, int size, String[] sortMethod) {
		List<Order> orders = new ArrayList<Order>();
		if(sortMethod.length > 1) {
			if(sortMethod[1].equals("desc")) {
				orders.add(new Order(Sort.Direction.DESC, sortMethod[0]));
			}else {
				orders.add(new Order(Sort.Direction.ASC, sortMethod[0]));
			}
		}else {
			orders.add(new Order(Sort.Direction.ASC,sortMethod[0]));
		}
		
		Pageable paginSort = PageRequest.of(page, size, Sort.by(orders));
		
		
		return UserMapper.INSTANCE.usersListToUserDtoList(usersRepository.findAll(paginSort));
		
	}

	@Override
	public Users getUserById(UUID id) {
		Optional<Users> user = usersRepository.findById(id);
		
		return (user.isPresent()) ? user.get() : null;
	}

	@Override
	public Users putUser(Users user, UserRequestBody userRequestBody) {
		if(userRequestBody.getEmail() !=null) {
			
			if((usersRepository.findByEmailIgnoreCase(userRequestBody.getEmail()) == null) || (user.getEmail().equals(userRequestBody.getEmail()))) {
				user.setEmail(userRequestBody.getEmail());									
			}else {
				throw new EmailExists("email already exists");
			}
		}
		if(userRequestBody.getName() != null)
			user.setName(userRequestBody.getName());
		if(userRequestBody.getLastName() != null)
			user.setLastName(userRequestBody.getLastName());
		
		user.setVacationStart(userRequestBody.getVacationStart());
		user.setVacationEnd(userRequestBody.getVacationEnd());
		
		if(userRequestBody.getRole() != null) {
			if(userRequestBody.getRole().contentEquals("DEVELOPER") || userRequestBody.getRole().contentEquals("ADMINISTRATOR")) {
				user.setRole(userRequestBody.getRole());				
			}else {
				throw new InvalidRole("invalid role"); 
			}
		}
		user.setModificationDate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
		usersRepository.save(user);
		return user;
	}

	@Override
	public long countRegisters() {
		return usersRepository.count();
	}

}
