package uk.ac.ljmu.fet.cs.cloud.examples.autoscaler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import hu.mta.sztaki.lpds.cloud.simulator.iaas.IaaSService;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.VirtualMachine;

public class TedsVI extends VirtualInfrastructure{

	public TedsVI(IaaSService cloud) {
		super(cloud);
	}

	@Override
	public void tick(long fires) {
		Date time=new Date((long)fires);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
        String day = simpleDateformat.format(time);
		final Iterator<String> kinds = vmSetPerKind.keySet().iterator();
		while (kinds.hasNext()) {
			final String kind = kinds.next();
			final ArrayList<VirtualMachine> vmset = vmSetPerKind.get(kind);
			if (day.equals("Mon") || day.equals("Tue") || day.equals("Wed") || day.equals("Thu")) {
				if (vmset.size() <= 400) {
					requestVM(kind);
				} else if (vmset.size() > 400){
					final ArrayList<VirtualMachine> unusedVMs = new ArrayList<VirtualMachine>();
					for (VirtualMachine vm : vmset) {
						if (vm.underProcessing.isEmpty() && vm.toBeAdded.isEmpty()) {
							unusedVMs.add(vm);
						}
					}
					for (int i = 0; i < unusedVMs.size() -1; i++) {
						destroyVM(unusedVMs.get(i));
					}
				}
			} else if (day.equals("Fri") || day.equals("Sat") || day.equals("Sun")) {
				if (vmset.size() < 1000) {
					requestVM(kind);
				}
			}
		}
	}
}
