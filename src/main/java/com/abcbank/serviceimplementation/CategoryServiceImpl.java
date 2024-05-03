package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.model.Category;
import com.abcbank.repository.CategoryRepo;
import com.abcbank.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepo categoryRepo;
	@Override
	public Object getAllCategory() {
		List<Map<String, Object>> list = new ArrayList<>();
		List<Category> categories = categoryRepo.findAll();

		for (Category c : categories) {

		Map<String, Object> map = new HashMap<>();
		map.put("categories", c.getCategory_desc());
		map.put("categoryCode", c.getCategory_code());
		list.add(map);
		}
		return list;
	}
}
