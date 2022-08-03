import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';

// maybe using 'await' here
i18n
  // detect user language
  // learn more: https://github.com/i18next/i18next-browser-languageDetector
  .use(LanguageDetector)
  // pass the i18n instance to react-i18next.
  .use(initReactI18next)
  .init({
    debug: true,
    fallbackLng: 'en',
    interpolation: {
        escapeValue: false, // not needed for react as it escapes by default
    },
    resources: {
        en: {
          translation: {
            "footer_legal": "© 1997 - 2022 AFRTN Television LLC. A Division of The McGwire Ishikawa Organization LLC. All Rights Reseved. Trademark & Copyright Notice.",
            "link_legal_notice": "Legal Notice",
            "link_jobs_career": "Jobs & Careers",
            "link_imprint": "Imprint",
            "menu_title_company": "Company",
            "menu_title_company_profile": "Company Profile",
            "shareholder_structure": "Shareholder Structure",
            "management": "Management",
            "supervisory_board": "Supervisory Board",
            "essentials": "Essentials",
            "head_quarters": "HHC - Headquarters & Headquarters Company",
            "cultural_affairs": "Cultural Affairs",
            "history": "History",
            "public_affairs": "Public Affairs",
            /* TV */
            "menu_title_hdtv": "Free-, Premium-, Pay-HDTV & Media Libraries",
            "hdtv_overview": "Overview",
            /* Places */
            "menu_title_places": "AFRTN.APP in 90 Countries Around The World in Stores, Apps, and Much More",

            "menu_title_news_media": "News & Media",

            "menu_title_customers": "Corporate Customers & Institutional Customers",

            "menu_title_governmental": "State & Local Government",

            "menu_title_partners": "Dealers, Partners, Artists, Bands, Labels, & Developers",

            "menu_title_awards": "Our Awards & Certifications",

            "menu_title_environmental": "Sustainable & Climate-Neutral",

            "menu_title_regions_languages": "Regions & Languages",

            "menu_title_contact": "Contact & Imprint"
          }
        },
        de: {
            translation: {
                "footer_legal": "© 1997 - 2022 AFRTN Television LLC. Ein Unternehmen der McGwire Ishikawa Organization LLC. Alle Rechte vorbehalten. Trademark & Copyright Notice.",
                "link_legal_notice": "Rechtliche Hinweise",
                "link_jobs_career": "Jobs & Karriere",
                "link_imprint": "Impressum",
                "menu_title_company": "Unternehmen",
                "menu_title_company_profile": "Unternehmensprofil",
                "shareholder_structure": "Aktionäre",
                "management": "Management",
                "supervisory_board": "Aufsichtsrat",
                "essentials": "Grundwerte",
                "head_quarters": "HHC - Headquarters & Headquarters Company",
                "cultural_affairs": "Kultur",
                "history": "Geschichte",
                "public_affairs": "Public Affairs",
                /* TV */
                "menu_title_hdtv": "Free-, Premium-, Pay-HDTV & Mediatheken",
                "hdtv_overview": "Übersicht",
                /* Places */
                "menu_title_places": "Nutzen Sie AFRTN.APP an diesen Orten, Apps und so vielem mehr",

                "menu_title_news_media": "News & Media",

                "menu_title_customers": "Unternehmenskunden & Institutionelle Kunden",

                "menu_title_governmental": "Öffentlicher Sektor, Städte & Gemeinden",

                "menu_title_partners": "Händler, Partner, Künstler, Bands, Labels & Entwickler",

                "menu_title_awards": "Zertifizierungen & Auszeichnungen",

                "menu_title_environmental": "Nachhaltig & Klimaneutral",

                "menu_title_regions_languages": "Regionen & Sprachen",

                "menu_title_contact": "Kontakt & Impressum"
            }
        }
    }
  });

export default i18n;
