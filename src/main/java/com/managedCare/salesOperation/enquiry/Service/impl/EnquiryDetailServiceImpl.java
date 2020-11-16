package com.managedCare.salesOperation.enquiry.Service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.managedCare.salesOperation.enquiry.Constatnt.CommonConstant;
import com.managedCare.salesOperation.enquiry.Entity.CreateContact;
import com.managedCare.salesOperation.enquiry.Entity.EnquiryDetails;
import com.managedCare.salesOperation.enquiry.Entity.EnquiryDetailList;
import com.managedCare.salesOperation.enquiry.Entity.GetEnquiry;
import com.managedCare.salesOperation.enquiry.Repository.CreateContactRepository;
import com.managedCare.salesOperation.enquiry.Repository.CreateEnquiryRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryAssignedByRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryCallReceivedRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryDetailRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryTypeRepository;
import com.managedCare.salesOperation.enquiry.Repository.GetEnquiryDetailsRepository;
import com.managedCare.salesOperation.enquiry.Repository.LanguageDetailsRepository;
import com.managedCare.salesOperation.enquiry.Repository.RecommdationRepository;
import com.managedCare.salesOperation.enquiry.Repository.ReferralRepository;
import com.managedCare.salesOperation.enquiry.Repository.RelationshipRepository;
import com.managedCare.salesOperation.enquiry.Repository.ServiceReferredRepository;
import com.managedCare.salesOperation.enquiry.Repository.UpdateEnquiryRepo;
import com.managedCare.salesOperation.enquiry.Repository.ViewListEnquiryDetailsRepo;
import com.managedCare.salesOperation.enquiry.Request.ContactDetailsDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryEditRequestDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryRequestDTO;
import com.managedCare.salesOperation.enquiry.Response.ActivityOfDailyLiving;
import com.managedCare.salesOperation.enquiry.Response.AssignedTo;
import com.managedCare.salesOperation.enquiry.Response.CallInformation;
import com.managedCare.salesOperation.enquiry.Response.CallReceivedBy;
import com.managedCare.salesOperation.enquiry.Response.CallerInformation;
import com.managedCare.salesOperation.enquiry.Response.ContactInformation;
import com.managedCare.salesOperation.enquiry.Response.EnquiriesResponseDto;
import com.managedCare.salesOperation.enquiry.Response.Enquiries_Type;
import com.managedCare.salesOperation.enquiry.Response.EnquiryResponseDTO;
import com.managedCare.salesOperation.enquiry.Response.EnquiryStatusResponse;
import com.managedCare.salesOperation.enquiry.Response.Language;
import com.managedCare.salesOperation.enquiry.Response.Realationship;
import com.managedCare.salesOperation.enquiry.Response.Recommendation;
import com.managedCare.salesOperation.enquiry.Response.SeenDoctor;
import com.managedCare.salesOperation.enquiry.Response.ServiceReferred;
import com.managedCare.salesOperation.enquiry.Response.SourceOfReferral;
import com.managedCare.salesOperation.enquiry.Response.VerbalAssessment;
import com.managedCare.salesOperation.enquiry.Service.EnquiryDetailService;
import com.managedCare.salesOperation.enquiry.objectmapping.ManagedCareBO;

@Service
public class EnquiryDetailServiceImpl implements EnquiryDetailService {

	@Autowired
	private EnquiryDetailRepository enquiry;
	@Autowired
	private CreateContactRepository createContactRepository;
	@Autowired
	EnquiryAssignedByRepository enquiryAssignedBy;
	@Autowired
	private CreateEnquiryRepository createEnquiry;
	@Autowired
	private ReferralRepository referralRepository;
	@Autowired
	private EnquiryCallReceivedRepository enquiryCallReceivedRepository;
	@Autowired
	private RelationshipRepository relationshipRepository;
	@Autowired
	private GetEnquiryDetailsRepository getEnquiryRepo;
	@Autowired
	private EnquiryTypeRepository enquiryTypeRepository;
	@Autowired
	private LanguageDetailsRepository languageDetailsRepository;
	
	@Autowired
	private ServiceReferredRepository serviceReferredRepository;
	
	@Autowired
	private RecommdationRepository recommdationRepository;
	@Autowired
	private UpdateEnquiryRepo updateEnquiry;
	@Autowired
	private ViewListEnquiryDetailsRepo viewEnquiryRepository;
	
	@Autowired
	private ManagedCareBO objManagedCareBO;

	@Override
	public List<String> getAllCustomerSupportFirstName() {
		List<String> supportFirstName = enquiry.findAllCustomerByFirstName();
		return supportFirstName;
	}

	@Override
	public List<String> getAllassignedToFirstName() {
		List<String> assignedByFirstName = enquiryAssignedBy.findbyAssignedFirtsName();
		return assignedByFirstName;
	}

	@Override
	public int createContact(ContactDetailsDTO contactCreateDTO) {
		CreateContact createContactEntity = new CreateContact();
		createContactEntity.setCallerId(contactCreateDTO.getCallerId());
		createContactEntity.setTitle(contactCreateDTO.getTitle());
		createContactEntity.setCallerName(contactCreateDTO.getCallerName());
		createContactEntity.setDob(contactCreateDTO.getDob());
		createContactEntity.setGender(contactCreateDTO.getGender());
		createContactEntity.setCode(contactCreateDTO.getCode());
		createContactEntity.setMobNumber(contactCreateDTO.getMobNumber());
		createContactEntity.setPermanentAddress(contactCreateDTO.getPermanentAddress());
		createContactEntity.setCurrentAddress(contactCreateDTO.getCurrentAddress());
		createContactEntity.setLanguage(contactCreateDTO.getLanguage());
		Date date = new Date();
		Timestamp currentTimeStamp = new Timestamp(date.getTime());
		createContactEntity.setCreatedDate(currentTimeStamp);
		createContactEntity.setCreadtedBy(contactCreateDTO.getCreatedBy());
		createContactEntity.setUpdatedBy(contactCreateDTO.getUpdatedBy());
		createContactEntity.setUpdatedDate(currentTimeStamp);
		return createContactRepository.save(createContactEntity).getCallerId();
	}

	@Override
	public List<String> getFullNameWithTitle() {
		List<String> fullNameWithTitle = createContactRepository.findAllCallerNameWithTitle();
		return fullNameWithTitle;
	}

	@Override
	public List<String> getAllReferralName() {
		List<String> referralByName = referralRepository.findAllReferralByName();
		return referralByName;
	}

	@Override
	public int createEnquiry(EnquiryRequestDTO enquiryRequest) {
		EnquiryDetails enquiryDetails = new EnquiryDetails();
		enquiryDetails.setEnquiryId(enquiryRequest.getEnquiryId());
		Date date = new Date();
		Timestamp currentTimeStamp = new Timestamp(date.getTime());
		enquiryDetails.setTitle(enquiryRequest.getTitle());
		enquiryDetails.setName(enquiryRequest.getName());
		enquiryDetails.setCode(enquiryRequest.getCode());
		enquiryDetails.setMobNo(enquiryRequest.getMobNo());
		enquiryDetails.setAddress(enquiryRequest.getAddress());
		enquiryDetails.setCreatedDate(currentTimeStamp);
		enquiryDetails.setContactAdded(enquiryRequest.getContactAdded());
		enquiryDetails.setRelationship(enquiryRequest.getRelationship());
		enquiryDetails.setReferral(enquiryRequest.getReferral());
		enquiryDetails.setLanguageDetails(enquiryRequest.getLanguageDetails());
		//enquiryDetails.setAssignedBy(enquiryRequest.getAssignedBy());
		//enquiryDetails.setRecommdationDetails(enquiryRequest.getRecommdationDetails());
		//enquiryDetails.setCallReceivedBy(enquiryRequest.getCallReceivedBy());
		//enquiryDetails.setServicesReferred(enquiryRequest.getServicesReferred());
		enquiryDetails.setAge(24);
		enquiryDetails.setServiceReferredId(1);
		enquiryDetails.setAssignedId(1);
		enquiryDetails.setCallReceivedId(1);
		enquiryDetails.setRecommdationId(1);
		enquiryDetails.setComplain(enquiryRequest.getComplain());
		enquiryDetails.setMedicalHistory(enquiryRequest.getMedicalHistory());
		enquiryDetails.setDocCheck(enquiryRequest.isDocCheck());
		enquiryDetails.setHeight(enquiryRequest.getHeight());
		enquiryDetails.setWeight(enquiryRequest.getWeight());
		enquiryDetails.setFeeding(enquiryRequest.getFeeding());
		enquiryDetails.setBathing(enquiryRequest.getBathing());
		enquiryDetails.setToileting(enquiryRequest.getToileting());
		enquiryDetails.setGrooming(enquiryRequest.getGrooming());
		enquiryDetails.setAmbulation(enquiryRequest.getAmbulation());
		enquiryDetails.setTransfer(enquiryRequest.getTransfer());
		enquiryDetails.setBedMobilty(enquiryRequest.getBedMobilty());
		enquiryDetails.setRecommendation(enquiryRequest.getRecommendation());
		enquiryDetails.setServicePreferred(enquiryRequest.getServicePreferred());
		//nr:issue in activities int datatype changed it to string and managed it accordingly
		//List<Integer> activities = enquiryRequest.getActivities();
		//StringBuilder strbul = new StringBuilder();
		//Iterator<Integer> iter = activities.iterator();
		//while (iter.hasNext()) {
		//	strbul.append(iter.next());
		//	if (iter.hasNext()) {
		//		strbul.append(",");
		//	}
		//}
		List<String> activities = enquiryRequest.getActivities();
		StringBuilder strbul = new StringBuilder();
		Iterator<String> iter = activities.iterator();
		while (iter.hasNext()) {
			strbul.append(iter.next());
			if (iter.hasNext()) {
				strbul.append(",");
			}
		}
		String strActivities = strbul.toString();
		enquiryDetails.setActivities(strActivities);
		enquiryDetails.setRehabCriteria(enquiryRequest.getRehabCriteria());
		enquiryDetails.setEnquiryTypeId(enquiryRequest.getEnquiryType());
		String status = CommonConstant.OPEN;
		enquiryDetails.setStatus(status);
		enquiryDetails.setLeadTime(0);
		enquiryDetails.setCreatedBy(enquiryRequest.getCreatedBy());
		enquiryDetails.setUpdatedBy(enquiryRequest.getUpdatedBy());
		enquiryDetails.setUpdatedDate(currentTimeStamp);
		return createEnquiry.save(enquiryDetails).getEnquiryId();
	}

	@Override
	public List<String> getAllRelationship() {
		List<String> relationship = relationshipRepository.findAllRelationship();
		return relationship;
	}

	/*
	 * @Override public EnquiryResponseDTO getEnquiryById(int id, EnquiryResponseDTO
	 * response)throws NullPointerException{ GetEnquiry enquiryDetails =
	 * getEnquiryRepo.findEnquiryById(id);
	 * if(enquiryDetails.equals(null)||enquiryDetails == null) { return null; }else
	 * { EnquiryResponseDTO enquiryDto = new ModelMapper().map(enquiryDetails,
	 * EnquiryResponseDTO.class); int relationshipId =
	 * enquiryDetails.getRelationshipId(); String name =
	 * relationshipRepository.getNameById(relationshipId);
	 * enquiryDto.setRelationshipName(name); int referralId =
	 * enquiryDetails.getReferralId(); String referralName =
	 * referralRepository.getReferralById(referralId);
	 * enquiryDto.setReferralName(referralName); int callerId =
	 * enquiryDetails.getCallerId(); String contactName =
	 * createContactRepository.getContactById(callerId);
	 * enquiryDto.setContactName(contactName); int enquiry_type=
	 * enquiryDetails.getEnquiryTypeId(); String activities =
	 * enquiryDetails.getActivities(); List<Integer> list =
	 * Arrays.stream(activities.split(",")).map(Integer::parseInt).collect(
	 * Collectors.toList()); enquiryDto.setActivities(list); return enquiryDto; } }
	 */

	@Override
	public EnquiriesResponseDto getEnquiryInfoById(int id, EnquiriesResponseDto response) throws NullPointerException {
		GetEnquiry enquiryDetails = getEnquiryRepo.findEnquiryById(id);
		if (enquiryDetails.equals(null) || enquiryDetails == null) {
			return null;
		} else {

			//Enquiries_Type enquiries_Type = objManagedCareBO.mapEnquireType();
			// response.setEnquiry_type(enquiries_Type);

			EnquiriesResponseDto enquiryDto = new ModelMapper().map(enquiryDetails, EnquiriesResponseDto.class);
			
			
			  int enquiryId=enquiryDetails.getEnquiryId(); 
			  Integer enquiryIdObj = new   Integer(enquiryId);
			  String enquireId = enquiryIdObj.toString(); 
			  String completeEnquire = "#ENQ" + enquireId; 
			  enquiryDto.setEnquire_id(completeEnquire);

			Enquiries_Type enquiry_type = new Enquiries_Type();
			enquiry_type.setValue(enquiryDetails.getEnquiryTypeId());
			int typeId=enquiryDetails.getEnquiryTypeId();
			
			String typeName=enquiryTypeRepository.getNameById(typeId);
			enquiry_type.setLabel(typeName);
			
			//enquiries_Type.setValue(enquiryDetails.getEnquiryTypeId());

			enquiryDto.setEnquiry_type(enquiry_type);
			
			enquiryDto.setEnquired_on(enquiryDetails.getCreatedDate());
			enquiryDto.setName(enquiryDetails.getName());
			enquiryDto.setGender(enquiryDetails.getGender());
			enquiryDto.setAge(enquiryDetails.getAge());
			

			CallInformation callInformation = new CallInformation();
			CallReceivedBy callReceivedBy = new CallReceivedBy();
			callReceivedBy.setId(enquiryDetails.getCallerId());
			callReceivedBy.setRole("");
			int receivedId=enquiryDetails.getCallReceivedId();
			
			String receivedName=enquiryCallReceivedRepository.getNameById(receivedId);
			
			callReceivedBy.setName(receivedName);

			AssignedTo assignedTo = new AssignedTo();
			assignedTo.setId(enquiryDetails.getAssignedId());
			assignedTo.setRole("");
			int assignedID=enquiryDetails.getAssignedId();
			String assignedName=enquiryAssignedBy.getNameById(assignedID);
			assignedTo.setName(assignedName);
			
			callInformation.setReceived_by(callReceivedBy);
			callInformation.setAssigned_to(assignedTo);
			enquiryDto.setCall_information(callInformation);

			CallerInformation callerInformation = new CallerInformation();
			Realationship realationship = new Realationship();
			realationship.setValue(enquiryDetails.getRelationshipId());
			
			int relationshipId = enquiryDetails.getRelationshipId();
			String name = relationshipRepository.getNameById(relationshipId);
			realationship.setLabel(name);
			//change done
			callerInformation.setCaller_contact_same(false);
			
			callerInformation.setAddress(enquiryDetails.getAddress());
			callerInformation.setMobile(enquiryDetails.getMobNo());
			callerInformation.setName(enquiryDetails.getName());
			callerInformation.setReleationship(realationship);
			enquiryDto.setCaller_information(callerInformation);

			List<Language> language1 = new ArrayList<>();

			Language langdet = new Language();
			langdet.setValue(enquiryDetails.getLanguageId());
			langdet.setLabel("English");
			//int languageId=enquiryDetails.getLanguageId();
			//String languageNam=languageDetailsRepository.getNameById(languageId);
			//langdet.setLabel(languageNam);
			
			Language langdetails = new Language();
			langdetails.setValue(enquiryDetails.getLanguageId());
			int langId=enquiryDetails.getLanguageId();
			String languageName=languageDetailsRepository.getNameById(langId);
			langdetails.setLabel(languageName);
			
			// langdetails.setLabel(enquiryDetails.getLanguageName());

			language1.add(langdet);
			language1.add(langdetails);
			
			/*
			 * Iterator iterator=language1.iterator(); while(iterator.hasNext()) {
			 * System.out.println(iterator.next()); }
			 */

			enquiryDto.setLanguage(language1);

			List<ContactInformation> ci = new ArrayList<>();

			ContactInformation contactInformation = new ContactInformation();
			contactInformation.setValue("mobile");
			contactInformation.setLabel(enquiryDetails.getMobNo());

			ContactInformation contactInformation1 = new ContactInformation();
			contactInformation1.setValue("address");
			contactInformation1.setLabel(enquiryDetails.getAddress());

			ci.add(contactInformation);
			ci.add(contactInformation1);
			enquiryDto.setContact_information(ci);

			enquiryDto.setComplaint(enquiryDetails.getComplain());
			enquiryDto.setMedical_history(enquiryDetails.getMedicalHistory());

			SeenDoctor seenDoctor = new SeenDoctor();
			//change done
			seenDoctor.setSeen(false);
			seenDoctor.setStatement(enquiryDetails.getStatement());
			enquiryDto.setSeen_a_doctor(seenDoctor);

			List<VerbalAssessment> verbal = new ArrayList<VerbalAssessment>();
			VerbalAssessment assessment = new VerbalAssessment();
			assessment.setValue("height");
			assessment.setLabel(enquiryDetails.getHeight());

			VerbalAssessment assessment1 = new VerbalAssessment();
			assessment1.setValue("weight");
			assessment1.setLabel(enquiryDetails.getWeight());
			verbal.add(assessment);
			verbal.add(assessment1);
			enquiryDto.setVerbal_assement(verbal);

			List<ActivityOfDailyLiving> act = new ArrayList<ActivityOfDailyLiving>();

			ActivityOfDailyLiving activity = new ActivityOfDailyLiving();

			activity.setTitle("Feeding");
			activity.setValue(enquiryDetails.getFeeding());

			ActivityOfDailyLiving activity1 = new ActivityOfDailyLiving();
			activity1.setTitle("Bathing");
			activity1.setValue(enquiryDetails.getBathing());

			ActivityOfDailyLiving activity2 = new ActivityOfDailyLiving();
			activity2.setTitle("Toileting");
			activity2.setValue(enquiryDetails.getToileting());

			act.add(activity);
			act.add(activity1);
			act.add(activity2);
			enquiryDto.setActivities_of_daily_living(act);
			
			List<String> activities=new ArrayList<String>();
			activities.add("NG Tube / PEG Tube");
			activities.add("Confusion / Memory Problems");
			activities.add("Tracheostomy");
			activities.add("Stoma");
			activities.add("Injections");
			enquiryDto.setOther_activities(activities);
			
			List<Recommendation> recommdationlist= new ArrayList<>();
			
			Recommendation  recommendation=new  Recommendation();
			recommendation.setId(enquiryDetails.getRecommdationId());
			
			int recommId=enquiryDetails.getRecommdationId();
			String recommName=recommdationRepository.getNameById(recommId);
			recommendation.setLabel(recommName);
			recommendation.setValue(enquiryDetails.getRecommdationId());
			int id4=enquiryDetails.getRecommdationId();
			String type=recommdationRepository.getTypeById(id4);
			recommendation.setType(type);
			recommdationlist.add(recommendation);
			
			Recommendation  recommendation1=new  Recommendation();
			recommendation1.setId(2);
			recommendation1.setLabel(recommName);
			recommendation1.setValue(2);
			recommendation1.setType("services");
			recommdationlist.add(recommendation1);
			
			
			
			
			enquiryDto.setRecommendations(recommdationlist);
			

			
			List<ServiceReferred> ser=new ArrayList<>();
			ServiceReferred serviceReferred=new ServiceReferred();
			serviceReferred.setValue(enquiryDetails.getServiceReferredId());
			
			int serviceReferredId = enquiryDetails.getServiceReferredId();
			String referralName = serviceReferredRepository.getNameById(serviceReferredId);
			
			serviceReferred.setLabel(referralName);
			ser.add(serviceReferred);
			enquiryDto.setServices_referred(ser);
			
			enquiryDto.setRegen_rehab_criteria(enquiryDetails.getRehabCriteria());
			
			enquiryDto.setOther_issues(enquiryDetails.getOther_issues());
			
			SourceOfReferral sourceOfReferral=new SourceOfReferral();
			sourceOfReferral.setValue(enquiryDetails.getReferralId());
			int referralID = enquiryDetails.getReferralId();
			String referralNames = referralRepository.getReferralById(referralID);
			sourceOfReferral.setLabel(referralNames);
			
			enquiryDto.setSource_of_referrel(sourceOfReferral);
			
			/*
			 * int relationshipId = enquiryDetails.getRelationshipId(); String name =
			 * relationshipRepository.getNameById(relationshipId);
			 */

			// enquiryDto.setRelationshipName(name);

			//int referralId = enquiryDetails.getReferralId();
			//String referralName = referralRepository.getReferralById(referralId);
			// enquiryDto.setReferralName(referralName);
			int callerId = enquiryDetails.getCallerId();
			String contactName = createContactRepository.getContactById(callerId);
			// enquiryDto.setContactName(contactName);

			int enquiry_Type = enquiryDetails.getEnquiryTypeId();
			String activities1= enquiryDetails.getActivities();
			
			/*
			 * List<Integer> list =
			 * Arrays.stream(activities.split(",")).map(Integer::parseInt)
			 * .collect(Collectors.toList());
			 */
			 
			// enquiryDto.setOther_activities(other_activities);

			return enquiryDto;
		}
	}

	@Override
	public List<String> getAllEnquiryTypeName() {
		List<String> enquiryType = enquiryTypeRepository.findAllEnquiryTypeByName();
		return enquiryType;
	}

	/*
	 * @Override public int editEnquery(int id, EnquiryEditRequestDTO
	 * enquiryRequest) { GetEnquiry enquiryDetails =
	 * updateEnquiry.findEnquiryById(id); if (enquiryDetails == null) { return 0; }
	 * else { enquiryDetails.setAddress(enquiryRequest.getAddress()); String address
	 * = enquiryDetails.getAddress();
	 * enquiryDetails.setTitle(enquiryRequest.getTitle()); String title =
	 * enquiryDetails.getTitle(); enquiryDetails.setName(enquiryRequest.getName());
	 * String name = enquiryDetails.getName();
	 * enquiryDetails.setCode(enquiryRequest.getCode()); String code =
	 * enquiryDetails.getCode(); enquiryDetails.setMobNo(enquiryRequest.getMobNo());
	 * String mobNo = enquiryDetails.getMobNo();
	 * enquiryDetails.setCallerId(enquiryRequest.getCallerId()); int callerId =
	 * enquiryDetails.getCallerId();
	 * enquiryDetails.setRelationshipId(enquiryRequest.getRelationshipId()); int
	 * relationshipId = enquiryDetails.getRelationshipId();
	 * enquiryDetails.setReferralId(enquiryRequest.getReferralId()); int referralId
	 * = enquiryDetails.getReferralId();
	 * enquiryDetails.setComplain(enquiryRequest.getComplain()); String complain =
	 * enquiryDetails.getAddress();
	 * enquiryDetails.setMedicalHistory(enquiryRequest.getMedicalHistory()); String
	 * medHistory = enquiryDetails.getMedicalHistory();
	 * enquiryDetails.setDocCheck(enquiryRequest.isDocCheck()); boolean docCheck =
	 * enquiryDetails.isDocCheck();
	 * enquiryDetails.setHeight(enquiryRequest.getHeight()); String height =
	 * enquiryDetails.getHeight();
	 * enquiryDetails.setWeight(enquiryRequest.getWeight()); String weight =
	 * enquiryDetails.getWeight();
	 * 
	 * enquiryDetails.setFeeding(enquiryRequest.getFeeding()); int feeding =
	 * enquiryDetails.getFeeding();
	 * enquiryDetails.setBathing(enquiryRequest.getBathing()); int bathing =
	 * enquiryDetails.getBathing();
	 * enquiryDetails.setToileting(enquiryRequest.getToileting()); int toileting =
	 * enquiryDetails.getToileting();
	 * enquiryDetails.setGrooming(enquiryRequest.getGrooming()); int grooming =
	 * enquiryDetails.getGrooming();
	 * enquiryDetails.setAmbulation(enquiryRequest.getAmbulation()); int ambulation
	 * = enquiryDetails.getAmbulation();
	 * enquiryDetails.setTransfer(enquiryRequest.getTransfer()); int transfer =
	 * enquiryDetails.getTransfer();
	 * enquiryDetails.setBedMobilty(enquiryRequest.getBedMobilty()); int bedMobility
	 * = enquiryDetails.getBedMobilty();
	 * 
	 * enquiryDetails.setRecommendation(enquiryRequest.getRecommendation()); int
	 * recommendation = enquiryDetails.getRecommendation();
	 * enquiryDetails.setServicePreferred(enquiryRequest.getServicePreferred()); int
	 * servicePreferred = enquiryDetails.getServicePreferred(); List<Integer>
	 * activities = enquiryRequest.getActivities(); StringBuilder strbul = new
	 * StringBuilder(); Iterator<Integer> iter = activities.iterator(); while
	 * (iter.hasNext()) { strbul.append(iter.next()); if (iter.hasNext()) {
	 * strbul.append(","); } } String strActivities = strbul.toString();
	 * enquiryDetails.setActivities(strActivities);
	 * enquiryDetails.setRehabCriteria(enquiryRequest.getRehabCriteria()); String
	 * rehab = enquiryDetails.getRehabCriteria();
	 * enquiryDetails.setEnquiryTypeId(enquiryRequest.getEnquiryTypeId()); int
	 * enquiryTypeId = enquiryDetails.getEnquiryTypeId();
	 * enquiryDetails.setStatus(enquiryRequest.getStatus()); String status =
	 * enquiryDetails.getStatus(); enquiryDetails.setLeadTime(0); int leadTime =
	 * enquiryDetails.getLeadTime(); String updatedBy =
	 * enquiryRequest.getUpdatedBy(); String createdBy =
	 * enquiryRequest.getCreatedBy(); Timestamp createdDate =
	 * enquiryRequest.getCreatedDate(); return updateEnquiry.updateEnquiry(address,
	 * title, name, code, mobNo, callerId, relationshipId, referralId, complain,
	 * medHistory, docCheck, height, weight, feeding, bathing, toileting, grooming,
	 * ambulation, transfer, bedMobility, recommendation, servicePreferred,
	 * strActivities, rehab, enquiryTypeId, status, leadTime, createdBy,
	 * createdDate, updatedBy, id); } }
	 */

	@Override
	public EnquiryStatusResponse getAllEnquiry(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("enquiryId"));
		Page<EnquiryDetailList> pagedResult = viewEnquiryRepository.findAll(paging);
		List<EnquiryDetailList> list = pagedResult.hasContent() ? pagedResult.getContent()
				: new ArrayList<EnquiryDetailList>();
		return objManagedCareBO.mapService(list, pageNo);
	}

}
