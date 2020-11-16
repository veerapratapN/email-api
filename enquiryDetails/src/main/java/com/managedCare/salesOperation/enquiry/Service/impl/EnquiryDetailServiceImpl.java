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
import com.managedCare.salesOperation.enquiry.Entity.CareCoordinator;
import com.managedCare.salesOperation.enquiry.Entity.CreateContact;
import com.managedCare.salesOperation.enquiry.Entity.EnquireDetails;
import com.managedCare.salesOperation.enquiry.Entity.EnquiryDetails;
import com.managedCare.salesOperation.enquiry.Entity.EnquiryDetailList;
import com.managedCare.salesOperation.enquiry.Entity.GetEnquiry;
import com.managedCare.salesOperation.enquiry.Entity.LeadType;
import com.managedCare.salesOperation.enquiry.Entity.Referral;
import com.managedCare.salesOperation.enquiry.Repository.AddEnquiryRepository;
import com.managedCare.salesOperation.enquiry.Repository.AssignedByRepository;
import com.managedCare.salesOperation.enquiry.Repository.CreateContactRepository;
import com.managedCare.salesOperation.enquiry.Repository.CreateEnquiryRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryAssignedByRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryCallReceivedRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryDetailRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryLeadTypeRepository;
import com.managedCare.salesOperation.enquiry.Repository.EnquiryTypeRepository;
import com.managedCare.salesOperation.enquiry.Repository.GetEnquiryDetailsRepository;
import com.managedCare.salesOperation.enquiry.Repository.LanguageDetailsRepository;
import com.managedCare.salesOperation.enquiry.Repository.RecommdationRepository;
import com.managedCare.salesOperation.enquiry.Repository.ReferralRepository;
import com.managedCare.salesOperation.enquiry.Repository.RelationshipRepository;
import com.managedCare.salesOperation.enquiry.Repository.ServiceReferredRepository;
import com.managedCare.salesOperation.enquiry.Repository.UpdateEnquiryRepo;
import com.managedCare.salesOperation.enquiry.Repository.ViewListEnquiryDetailsRepo;
import com.managedCare.salesOperation.enquiry.Request.AssignedByDto;
import com.managedCare.salesOperation.enquiry.Request.ContactDetailsDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryEditRequestDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryLeadTypeDto;
import com.managedCare.salesOperation.enquiry.Request.EnquiryReferralDto;
import com.managedCare.salesOperation.enquiry.Request.EnquiryRequestDTO;
import com.managedCare.salesOperation.enquiry.Response.ActivityOfDailyLiving;
import com.managedCare.salesOperation.enquiry.Response.AddContactResponse;
import com.managedCare.salesOperation.enquiry.Response.AssignedTo;
import com.managedCare.salesOperation.enquiry.Response.CallInformation;
import com.managedCare.salesOperation.enquiry.Response.CallReceivedBy;
import com.managedCare.salesOperation.enquiry.Response.CallerInformation;
import com.managedCare.salesOperation.enquiry.Response.ContactInformation;
import com.managedCare.salesOperation.enquiry.Response.EnquiriesResponseDto;
import com.managedCare.salesOperation.enquiry.Response.Enquiries_Type;
import com.managedCare.salesOperation.enquiry.Response.EnquiryResponse;
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
	private EnquiryLeadTypeRepository enquiryLeadTypeRepository;

	@Autowired
	private AddEnquiryRepository addEnquiryRepository;
	@Autowired
	private ReferralRepository referralRepository;
	// new repo added
	@Autowired
	private EnquiryLeadTypeRepository leadTypeRepository;
	@Autowired
	private AssignedByRepository assignedByRepository;

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
		// createContactEntity.setCreatedDate(contactCreateDTO.getCreatedDate());

		createContactEntity.setCreatedDate(currentTimeStamp);
		createContactEntity.setCreadtedBy(contactCreateDTO.getCreatedBy());
		createContactEntity.setUpdatedBy(contactCreateDTO.getUpdatedBy());
		createContactEntity.setUpdatedDate(currentTimeStamp);
		// createContactEntity.setUpdatedDate(contactCreateDTO.getUpdatedDate());

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

		EnquireDetails enquire = new EnquireDetails();
		enquire.setEnquired_on(enquiryRequest.getEnquired_on());
		enquire.setLead_type_id(enquiryRequest.getLead_type_id());
		enquire.setReceived_id(enquiryRequest.getReceived_id());
		enquire.setAssigned_to(enquiryRequest.getAssigned_to());
		enquire.setSource_of_referreal(enquiryRequest.getSource_of_referreal());
		enquire.setComplain(enquiryRequest.getComplain());
		enquire.setMedicalHistory(enquiryRequest.getMedicalHistory());
		enquire.setSeenDoctor(enquiryRequest.isDoctor());
		enquire.setHeight(enquiryRequest.getHeight());
		enquire.setHeight(enquiryRequest.getWeight());
		enquire.setEnquiry_status(enquiryRequest.getEnquiry_status());
		enquire.setContact_information(enquiryRequest.getContact_information());

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
		enquire.setActivities(strActivities);
		enquire.setAdl(enquiryRequest.getAdl());

		return addEnquiryRepository.save(enquire).getEnquiryId();

		/*
		 * String strActivities = strbul.toString();
		 * enquiryDetails.setActivities(strActivities);
		 * 
		 * return addEnquiryRepository.save(enquire).getEnquiryId();
		 * 
		 * 
		 * /* EnquiryDetails enquiryDetails = new EnquiryDetails();
		 * enquiryDetails.setEnquiryId(enquiryRequest.getEnquiryId()); Date date = new
		 * Date(); Timestamp currentTimeStamp = new Timestamp(date.getTime());
		 * enquiryDetails.setTitle(enquiryRequest.getTitle());
		 * enquiryDetails.setName(enquiryRequest.getName());
		 * enquiryDetails.setCode(enquiryRequest.getCode());
		 * enquiryDetails.setMobNo(enquiryRequest.getMobNo());
		 * enquiryDetails.setAddress(enquiryRequest.getAddress()); // newly add field
		 * 
		 * enquiryDetails.setAge(24);
		 */
		// enquiryDetails.setServiceReferredId(1);
		// enquiryDetails.setCallReceivedId(1);
		// enquiryDetails.setRecommdationId(1);
		// enquiryDetails.setAssignedId(1);
		// enquiryDetails.setLanguageId(1);

		/*
		 * enquiryDetails.setCreatedDate(currentTimeStamp);
		 * enquiryDetails.setContactAdded(enquiryRequest.getContactAdded());
		 * enquiryDetails.setRelationship(enquiryRequest.getRelationship());
		 * enquiryDetails.setReferral(enquiryRequest.getReferral());
		 * enquiryDetails.setLanguageDetails(enquiryRequest.getLanguageDetails());
		 * 
		 * // changes for remove hardcoding the value
		 * enquiryDetails.setAssignedBy(enquiryRequest.getAssignedBy());
		 * enquiryDetails.setRecommdationDetails(enquiryRequest.getRecommdationDetails()
		 * ); enquiryDetails.setCallReceivedBy(enquiryRequest.getCallReceivedBy());
		 * enquiryDetails.setServicesReferred(enquiryRequest.getServicesReferred());
		 */ // enquiryDetails.setAge(enquiryRequest.getAge());
		// enquiryDetails.setGender(enquiryRequest.getGender());
		/*
		 * enquiryDetails.setStatement(enquiryRequest.getStatement());
		 * 
		 * enquiryDetails.setComplain(enquiryRequest.getComplain());
		 * enquiryDetails.setMedicalHistory(enquiryRequest.getMedicalHistory());
		 * enquiryDetails.setDocCheck(enquiryRequest.isDocCheck());
		 * enquiryDetails.setHeight(enquiryRequest.getHeight());
		 * enquiryDetails.setWeight(enquiryRequest.getWeight());
		 * enquiryDetails.setFeeding(enquiryRequest.getFeeding());
		 * enquiryDetails.setBathing(enquiryRequest.getBathing());
		 * enquiryDetails.setToileting(enquiryRequest.getToileting());
		 * enquiryDetails.setGrooming(enquiryRequest.getGrooming());
		 * enquiryDetails.setAmbulation(enquiryRequest.getAmbulation());
		 * enquiryDetails.setTransfer(enquiryRequest.getTransfer());
		 * enquiryDetails.setBedMobilty(enquiryRequest.getBedMobilty());
		 * enquiryDetails.setRecommendation(enquiryRequest.getRecommendation());
		 * enquiryDetails.setServicePreferred(enquiryRequest.getServicePreferred());
		 * List<String> activities = enquiryRequest.getActivities(); StringBuilder
		 * strbul = new StringBuilder(); Iterator<String> iter = activities.iterator();
		 * while (iter.hasNext()) { strbul.append(iter.next()); if (iter.hasNext()) {
		 * strbul.append(","); } }
		 */

		/*
		 * while (iter.hasNext()) { strbul.append(iter.next()); if (iter.hasNext()) {
		 * strbul.append(","); } }
		 */
		/*
		 * String strActivities = strbul.toString();
		 * enquiryDetails.setActivities(strActivities);
		 * enquiryDetails.setRehabCriteria(enquiryRequest.getRehabCriteria());
		 * enquiryDetails.setEnquiryTypeId(enquiryRequest.getEnquiryType()); String
		 * status = CommonConstant.OPEN; enquiryDetails.setStatus(status);
		 * enquiryDetails.setLeadTime(0);
		 * enquiryDetails.setCreatedBy(enquiryRequest.getCreatedBy());
		 * enquiryDetails.setUpdatedBy(enquiryRequest.getUpdatedBy());
		 * enquiryDetails.setUpdatedDate(currentTimeStamp);
		 */

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
	public EnquiryResponse getEnquiryInfoById(int id, EnquiryResponse response) throws NullPointerException {
		GetEnquiry enquiryDetails = getEnquiryRepo.findEnquiryById(id);
		if (enquiryDetails.equals(null) || enquiryDetails == null) {
			return null;
		} else {
			EnquiryResponse enquiryDto = new ModelMapper().map(enquiryDetails, EnquiryResponse.class);
			enquiryDetails.setEnquiryId(enquiryDetails.getEnquiryId());
			enquiryDto.setEnquired_on(enquiryDetails.getEnquired_on());

			com.managedCare.salesOperation.enquiry.Response.LeadType leadtype = new com.managedCare.salesOperation.enquiry.Response.LeadType();
			leadtype.setValue(enquiryDetails.getLead_type_id());
			int leadId = enquiryDetails.getLead_type_id();
			String leadName = enquiryLeadTypeRepository.getNameById(leadId);
			leadtype.setLabel(leadName);
			enquiryDto.setLead_type(leadtype);

			SourceOfReferral referral = new SourceOfReferral();
			referral.setValue(enquiryDetails.getSource_of_referreal());

			int referralId = enquiryDetails.getSource_of_referreal();

			String refName = referralRepository.getReferralById(referralId);
			referral.setLabel(refName);

			enquiryDto.setSource_of_referreal(referral);

			CallReceivedBy callReceivedBy = new CallReceivedBy();
			callReceivedBy.setValue(enquiryDetails.getReceived_id());
			int receivedId = enquiryDetails.getReceived_id();
			String receivedName = enquiryCallReceivedRepository.getNameById(receivedId);
			callReceivedBy.setLabel(receivedName);
			enquiryDto.setCall_received(callReceivedBy);

			AssignedTo assignedTo = new AssignedTo();
			assignedTo.setValue(enquiryDetails.getAssigned_to());

			long assignId = enquiryDetails.getAssigned_to();

			String assignName = assignedByRepository.getAssignedToId(assignId);
			assignedTo.setLabel(assignName);
			enquiryDto.setAssigned_To(assignedTo);

			// enquiryDto.setLead_type_id(enquiryDetails.getLead_type_id());
			// enquiryDto.setReceived_id(enquiryDetails.getReceived_id());
			// enquiryDto.setAssigned_to(enquiryDetails.getAssigned_to());
			// enquiryDto.setSource_of_referreal(enquiryDetails.getSource_of_referreal());
			enquiryDto.setComplain(enquiryDetails.getComplain());
			enquiryDto.setMedicalHistory(enquiryDetails.getMedicalHistory());
			enquiryDto.setSeenDoctor(enquiryDetails.isSeenDoctor());
			enquiryDto.setHeight(enquiryDetails.getHeight());
			enquiryDto.setWeight(enquiryDetails.getWeight());
			enquiryDto.setEnquiry_status(enquiryDetails.getEnquiry_status());

			AddContactResponse addContact = new AddContactResponse();
			// addContact.setContactId(enquiryDetails.getCaller_id());
			enquiryDto.setContact_information(enquiryDetails.getContact_information());

			enquiryDto.setActivities(enquiryDetails.getActivities());
			enquiryDto.setAdl(enquiryDetails.getAdl());

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

	@Override
	public List<LeadType> getAllLeadType(EnquiryLeadTypeDto leadTypeDto) {
		List<LeadType> type = leadTypeRepository.returnLeadType(leadTypeDto.getLeadTypeId());
		return type;
	}

	@Override
	public List<Referral> getAllSourceOfRefferal(EnquiryReferralDto referralDto) {
		List<Referral> sourceOfReferral = referralRepository.returnSourceOfReferral(referralDto.getReferralId());

		return sourceOfReferral;
	}

	@Override
	public List<CareCoordinator> getAllAssignedBy(AssignedByDto assignedByDto) {

		List<CareCoordinator> assigned = assignedByRepository.returnAssignedBy(assignedByDto.getCarecoordinator_id());

		return assigned;
	}

	@Override
	public int editEnquery(int id, EnquiryEditRequestDTO enquiryRequest) {
		GetEnquiry enquiryDetails = updateEnquiry.findEnquiryById(id);
		if (enquiryDetails == null) {
			return 0;
		} else {

			enquiryDetails.setEnquired_on(enquiryRequest.getEnquired_on());
			Timestamp enquired_on = enquiryRequest.getEnquired_on();

			enquiryDetails.setLead_type_id(enquiryRequest.getLead_type_id());
			int lead_type_id = enquiryRequest.getLead_type_id();

			enquiryDetails.setReceived_id(enquiryRequest.getReceived_id());
			int received_id = enquiryRequest.getReceived_id();

			enquiryDetails.setAssigned_to(enquiryRequest.getAssigned_to());
			int assigned_to = enquiryRequest.getAssigned_to();

			enquiryDetails.setSource_of_referreal(enquiryRequest.getSource_of_referreal());
			int source_of_referreal = enquiryRequest.getSource_of_referreal();

			enquiryDetails.setComplain(enquiryRequest.getComplain());
			String complain = enquiryRequest.getComplain();

			enquiryDetails.setMedicalHistory(enquiryRequest.getMedicalHistory());
			String medicalHistory = enquiryRequest.getMedicalHistory();

			enquiryDetails.setSeenDoctor(enquiryRequest.isSeenDoctor());
			boolean seenDoctor = enquiryRequest.isSeenDoctor();

			enquiryDetails.setHeight(enquiryRequest.getHeight());
			String height = enquiryRequest.getHeight();

			enquiryDetails.setWeight(enquiryRequest.getWeight());
			String weight = enquiryRequest.getWeight();

			enquiryDetails.setEnquiry_status(enquiryRequest.getEnquiry_status());
			String enquiry_status = enquiryRequest.getEnquiry_status();

			List<String> activities1 = enquiryRequest.getActivities();
			StringBuilder strbul = new StringBuilder();
			Iterator<String> iter = activities1.iterator();
			while (iter.hasNext()) {
				strbul.append(iter.next());
				if (iter.hasNext()) {
					strbul.append(",");
				}
			}
			String strActivities = strbul.toString();

			enquiryDetails.setActivities(strActivities);
			List<String> activities=enquiryRequest.getActivities();

			// enquiryDetails.setActivities(enquiryRequest.getActivities());
			// String activities=enquiryRequest.getActivities();

			enquiryDetails.setAdl(enquiryRequest.getAdl());
			String adl = enquiryRequest.getAdl();

			return updateEnquiry.updateEnquiry(enquired_on, lead_type_id, received_id, assigned_to, source_of_referreal,
					complain, medicalHistory, seenDoctor, height, weight, enquiry_status, adl,activities, received_id);
			// 
		}

	}

}

//enquiryDetails.setContact_information(enquiryRequest.getContact_information());
// AddEnquiryContact
// contact_information=enquiryRequest.getContact_information();
