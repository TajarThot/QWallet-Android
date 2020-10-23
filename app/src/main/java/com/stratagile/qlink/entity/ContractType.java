package com.stratagile.qlink.entity;

/**
 * Created by James on 6/12/2018.
 * Stormbird in Singapore
 */
//NB: if you add to this list, place your new entry at the end of the list before 'CREATION' because the ordinal is used in storage

public enum ContractType
{
    NOT_SET,
    ETHEREUM,
    ERC20,
    ERC721,
    ERC875_LEGACY,
    ERC875,
    OTHER,
    CURRENCY,
    DELETED_ACCOUNT,
    ERC721_LEGACY,
    ERC721_TICKET,
    ERC721_UNDETERMINED, //when we receive an ERC721 we don't know what kind it is
    CREATION //Placeholder for generic, should be at end of list
}
