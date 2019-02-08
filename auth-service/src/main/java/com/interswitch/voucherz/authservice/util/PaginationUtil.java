package com.interswitch.voucherz.authservice.util;

import com.interswitch.voucherz.authservice.models.Merchant;
import org.springframework.hateoas.PagedResources;

public class PaginationUtil {

    public static String createLinkHeader(PagedResources<Merchant> pr){
        final StringBuilder linkHeader = new StringBuilder();
        linkHeader.append(buildLinkHeader(  pr.getLinks("first").get(0).getHref(),"first"));
        linkHeader.append(", ");
        linkHeader.append(buildLinkHeader( pr.getLinks("next").get(0).getHref(),"next"));
        return linkHeader.toString();
    }


    private static String buildLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }
}
