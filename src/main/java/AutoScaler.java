import java.io.File;
import java.io.IOException;

import hu.mta.sztaki.lpds.cloud.simulator.examples.jobhistoryprocessor.DCFJob;
import hu.mta.sztaki.lpds.cloud.simulator.helpers.trace.FileBasedTraceProducerFactory;
import hu.mta.sztaki.lpds.cloud.simulator.helpers.trace.GenericTraceProducer;
import hu.mta.sztaki.lpds.cloud.simulator.helpers.trace.TraceManagementException;

public class AutoScaler {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IOException, TraceManagementException {

		String traceLoc = "M:\\git\\dissect-cf-examples\\src\\main\\java\\2013-fall-week2-abbreviated.log";
		File trace = new File(traceLoc);
		GenericTraceProducer producer = null;
		int maxTotalProcs = 0;
		int to = 47585;
		int from = 0;
		
		
		if(trace.exists()) {
			producer = FileBasedTraceProducerFactory.getProducerFromFile(traceLoc, from, to, false, maxTotalProcs, DCFJob.class);
		}
		
	}

}
