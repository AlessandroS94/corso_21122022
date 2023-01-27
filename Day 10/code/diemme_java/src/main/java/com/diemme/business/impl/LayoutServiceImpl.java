package com.diemme.business.impl;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.LayoutService;
import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.StatusType;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.FileLayoutRepository;
import com.diemme.repository.mysql.LayoutRepository;
import com.diemme.wrapperForm.FormWrapperLayout;

@Service
@Transactional
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private LayoutRepository layoutRepository;

	@Autowired
	private FileLayoutRepository fileLayoutRepository;

	@Override
	public Layout getLayout(Long id) throws BusinessException {
		return layoutRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Layout", "id", id));
	}

	@Override
	public Layout updateLayout(Long id, Layout layout, List<MultipartFile> contentImg, User userAuth)
			throws BusinessException {

		int i = 1;
		Set<FileLayout> file = new HashSet<FileLayout>();
		List<byte[]> Listbytes = new ArrayList<byte[]>();
		Layout layoutOld = new Layout();
		Layout layoutSave = new Layout();

		layoutOld = layoutRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Layout", "id", id));
		layoutSave.setId(id);
		layoutSave.setCompleted(layout.getCompleted());
		layoutSave.setDescription(layout.getDescription());
		layoutSave.setName(layout.getName());
		layoutSave.setUsers(layoutOld.getUsers());
		layoutSave.setStatus(layout.getStatus());
		layoutSave.setModifyDate(ZonedDateTime.now());
		ZonedDateTime dateCreation = layoutOld.getInsertDate();
		if (contentImg.size() == 1 && contentImg.get(0).isEmpty()) {
				Set<FileLayout> oldFiles = layoutOld.getFileLayouts();
				layoutSave.setFileLayouts(oldFiles);
		} else {
			try {
				for (MultipartFile bytes : contentImg) {
					Listbytes.add(bytes.getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (byte[] fileContent : Listbytes) {
				FileLayout fileLayoutSave = new FileLayout();
				fileLayoutSave.setContentImg(fileContent);
				fileLayoutSave.setName((String) "layout: " + layoutSave.getName() + ", file N° " + i);
				fileLayoutSave = fileLayoutRepository.save(fileLayoutSave);
				i++;
				file.add(fileLayoutSave);

			}

			layoutSave.setFileLayouts(file);
		}

		layoutSave = layoutRepository.save(layoutSave);
		layoutSave.setInsertDate(dateCreation);
		return layoutRepository.save(layoutSave);
	}

	@Override
	public Layout updateProductorLayout(Long id, Layout layout, User userAuth) throws BusinessException {

		Layout layoutOld = new Layout();
		Layout layoutSave = new Layout();

		layoutOld = layoutRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Layout", "id", id));
		layoutSave.setId(layoutOld.getId());
		layoutSave.setCompleted(layoutOld.getCompleted());
		layoutSave.setDescription(layoutOld.getDescription());
		layoutSave.setName(layoutOld.getName());
		layoutSave.setUsers(layoutOld.getUsers());
		layoutSave.setStatus(layout.getStatus());
		layoutSave.setModifyDate(ZonedDateTime.now());
		ZonedDateTime dateCreation = layoutOld.getInsertDate();
		Set<FileLayout> oldFiles = layoutOld.getFileLayouts();
		layoutSave.setFileLayouts(oldFiles);
		layoutSave = layoutRepository.save(layoutSave);
		layoutSave.setInsertDate(dateCreation);
		layoutRepository.save(layoutSave);
		return layoutSave;

	}

	@Override
	public Layout updateClientLayout(Long id, Layout layout, User userAuth) throws BusinessException {

		Layout layoutOld = new Layout();
		Layout layoutSave = new Layout();

		layoutOld = layoutRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Layout", "id", id));
		layoutSave.setId(layoutOld.getId());
		layoutSave.setCompleted(layout.getCompleted());
		layoutSave.setDescription(layoutOld.getDescription());
		layoutSave.setName(layoutOld.getName());
		layoutSave.setUsers(layoutOld.getUsers());
		layoutSave.setStatus(layoutOld.getStatus());
		layoutSave.setModifyDate(ZonedDateTime.now());
		ZonedDateTime dateCreation = layoutOld.getInsertDate();
		Set<FileLayout> oldFiles = layoutOld.getFileLayouts();
		layoutSave.setFileLayouts(oldFiles);
		layoutSave = layoutRepository.save(layoutSave);
		layoutSave.setInsertDate(dateCreation);
		layoutRepository.save(layoutSave);
		return layoutSave;

	}

	@Override
	public void createLayout(FormWrapperLayout layoutWrapper, List<MultipartFile> contentImg, User userAuth)
			throws BusinessException {

		int i = 1;
		Set<FileLayout> file = new HashSet<FileLayout>();
		Layout layoutSave = new Layout();
		List<byte[]> Listbytes = new ArrayList<byte[]>();
		Set<User> usersLayout = new HashSet<User>();

		for (User user : layoutWrapper.getUserClient()) {
			usersLayout.add(user);
		}

		usersLayout.add(userAuth);
		layoutSave.setName(layoutWrapper.getLayout().getName());
		layoutSave.setDescription(layoutWrapper.getLayout().getDescription());
		layoutSave.setCompleted(false);
		layoutSave.setUsers(usersLayout);
		layoutSave.setStatus(StatusType.IN_PROGRESS);
		layoutSave = layoutRepository.save(layoutSave);

		try {
			for (MultipartFile bytes : contentImg) {
				Listbytes.add(bytes.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (byte[] fileContent : Listbytes) {

			FileLayout fileLayoutSave = new FileLayout();
			fileLayoutSave.setContentImg(fileContent);
			fileLayoutSave.setName((String) "layout: " + layoutSave.getName() + ", file N° " + i);
			fileLayoutRepository.save(fileLayoutSave);
			file.add(fileLayoutSave);
			i++;
		}

		layoutSave.setFileLayouts(file);
		layoutRepository.save(layoutSave);
	}

	@Override
	public Page<Layout> getAllLayoutPageable(Integer page, Integer size) throws BusinessException {
		return layoutRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public void deleteLayout(Long id) throws BusinessException {

		layoutRepository.deleteById(id);
	}

	@Override
	public Page<Layout> getLayoutsByUserId(Long id, Integer page, Integer size) throws BusinessException {
		Pageable pageable = PageRequest.of(page, size);
		Page<Layout> pageLayout = layoutRepository.getLayoutByUserId(pageable, id);
		return pageLayout;
	}

	@Override
	public Set<User> getAllUsersLayout(Long id) throws BusinessException {
		return layoutRepository.getUsersLayout(id);
	}

	@Override
	public Page<Layout> getLayoutsByStatus(StatusType status, Integer page, Integer size) throws BusinessException {
		Pageable pageable = PageRequest.of(page, size);
		Page<Layout> pageLayout = layoutRepository.findByStatus(pageable, status);
		return pageLayout;
	}

	@Override
	public Page<Layout> getMyLayoutsByStatus(Long id, StatusType status, Integer page, Integer size)
			throws BusinessException {
		Pageable pageable = PageRequest.of(page, size);
		Page<Layout> pageLayout = layoutRepository.getLayoutByUserIdAndStatus(pageable, id, status);
		return pageLayout;
	}

}
