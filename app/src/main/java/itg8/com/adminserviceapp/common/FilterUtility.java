package itg8.com.adminserviceapp.common;


import java.util.ArrayList;
import java.util.List;

import itg8.com.adminserviceapp.tender.model.TenderModel;

/**
 * Created by Android itg 8 on 9/20/2017.
 */

public class FilterUtility {

    private FilterBuilder filterBuilder;

    public FilterUtility(FilterBuilder filterBuilder) {

        this.filterBuilder = filterBuilder;
    }

    public List<TenderModel> getFilteredList()
    {
        return filterBuilder.ticketModelList;
    }



    public static class FilterBuilder{
        private List<TenderModel> ticketModelList;


        public FilterBuilder createBuilder(List<TenderModel> tblStateList)
        {
            this.ticketModelList = new ArrayList<>();
            this.ticketModelList.addAll(tblStateList);
            return this;
        }



        public FilterBuilder setFilter()
        {
//            List<TicketModel> tempOpenTicket = new ArrayList<>();
//
//            for (TicketModel ticket: ticketModelList) {
////                 if(ticket.getStatus()== null||ticket.getStatus().equals(CommonMethod.STATUS_OPEN));
////                 {
////                     tempOpenTicket.add(ticket);
////
////                 }

//            }

//            ticketModelList.clear();
//            ticketModelList.addAll(tempOpenTicket);
            return this;
        }




        public FilterUtility build()
        {
             return new FilterUtility(this);
        }


    }
}
