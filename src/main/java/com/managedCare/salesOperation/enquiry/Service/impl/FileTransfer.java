package com.managedCare.salesOperation.enquiry.Service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.managedCare.salesOperation.enquiry.Entity.QuoteAttachement;
import com.managedCare.salesOperation.enquiry.Exception.RecordNotFoundException;
import com.managedCare.salesOperation.enquiry.Repository.QuoteRepository;

@Service
public class FileTransfer {
	
	
	@Value("${file.transfer.destination.location}")
	String destinationFilePath;
	
	@Value("${file.transfer.maxbuffer.size:512}")
	int bufferMaxsize;
	
	@Value("${file.mimetype.standard:.pdf}")
	String filemimetype;
	
	@Autowired
	QuoteRepository quoteRepository;
	
	
	
	public void moveFileToDestnationLocation(int quoteID, String  sourceFilePath) throws IOException {
        
		FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        File destinationFile = null;
        File sourceFile = null;
        int bufferSize;
        QuoteAttachement quoteAttachement = null;
        try {
            sourceFile = new File(sourceFilePath);
            sourceFile.getAbsoluteFile().mkdirs();
            
            destinationFile = new File(destinationFilePath + quoteID+"_" + LocalDate.now() +filemimetype);
            fileInputStream = new FileInputStream(sourceFile);
            fileOutputStream = new FileOutputStream(destinationFile);

            byte[] bufffer = new byte[bufferMaxsize];
            while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
                fileOutputStream.write(bufffer, 0, bufferSize);
            }
             quoteAttachement = new QuoteAttachement();
            quoteAttachement.setQuoteId(quoteID);
            quoteAttachement.setDestnationFileLocation(destinationFile.getPath());
            quoteRepository.save(quoteAttachement);
            
            
            
        } finally {
        	if(fileInputStream != null)
            fileInputStream.close();
        	if(fileOutputStream != null)
            fileOutputStream.close();
            
        }
        
    }
	public QuoteAttachement getDocById(Integer id) throws RecordNotFoundException 
    {
        Optional<QuoteAttachement> getdoc = quoteRepository.findById(id);
         
        if(getdoc.isPresent()) {
            return getdoc.get();
        } else {
            throw new RecordNotFoundException("No  record exist for given id");
        }
    }
	
	//getall
	public List<QuoteAttachement> findallData(){
		List<QuoteAttachement> getalldoc= quoteRepository.findAll();
    
   if(getalldoc.size() > 0) {
       return getalldoc;
   } else {
       return new ArrayList<QuoteAttachement>();
   }
}
	
	  public QuoteAttachement createOrUpdateData(QuoteAttachement entity) throws RecordNotFoundException 
	    {
	        Optional<QuoteAttachement> doc = quoteRepository.findById(entity.getQuoteId());
	         
	        if(doc.isPresent()) 
	        {
	        	QuoteAttachement newEntity = doc.get();
	        	newEntity.setDestnationFileLocation(entity.getDestnationFileLocation());
	          
	 
	            newEntity = quoteRepository.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = quoteRepository.save(entity);
	             
	            return entity;
	        }
	    } 
	  public QuoteAttachement updateDocs(QuoteAttachement quoteAttachementdoc) {
	        return quoteRepository.save(quoteAttachementdoc);
	    }
	/*
	 * public QuoteAttachement updatedata(UUID id, VehicleUpdateDTO
	 * vehicleUpdateDTO) {
	 * 
	 * if (vehicleRepository.findById(id).isPresent()){ Vehicle existingVehicle =
	 * vehicleRepository.findById(id).get();
	 * 
	 * existingVehicle.setMake(vehicleUpdateDTO.getMake());
	 * existingVehicle.setModel(vehicleUpdateDTO.getModel());
	 * 
	 * Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
	 * 
	 * return new VehicleQueryDTO(updatedVehicle.getId(),
	 * updatedVehicle.getVehicleIdentityNumber(), updatedVehicle.getMake(),
	 * updatedVehicle.getModel()); }else{ return null; } }
	 */

}
